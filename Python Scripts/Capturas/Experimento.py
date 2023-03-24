import pyautogui as pg
import time
from PIL import Image
from pytesseract import pytesseract

path_to_image = 'my_screenshot.png'
Todo=False # Si esta puesto en False priorizara la busqueda de Exp, sino hara la media de todo
mision=[]
misiones=[]
i=""
seguir=True
buena=0
medias=[]  
conseguido=True
tie=0
exp=0
oro=0


def isNumeric(s):
    try:
        float(s)
        return True
    except ValueError:
        return False

def tiempo(s):
    cant = len(s)
    if(cant==3):
        total=int(s[(cant-2):(cant)])+(60*int(s[0]))
    elif(cant==4):
        total=int(s[(cant-2):(cant)])+(60*int(s[(cant-4):(cant-2)]))
    
    return total

time.sleep(2)
conta=0

while True:
    while conseguido:
        print(conta)
        if(conta==0):
            pg.moveTo(1047, 857)
            pg.click()
        elif(conta==1):
            pg.moveTo(1774, 850)
            pg.click()
        elif(conta==10):
            conseguido= False
        conta=conta+1
        im2 = pg.screenshot(region=(1160, 869, 715, 62))
        im2.save(path_to_image)
        path_to_tesseract = r'C:/Program Files/Tesseract-OCR/tesseract.exe'
        #Define path to image

        #Point tessaract_cmd to tessaract.exe
        pytesseract.tesseract_cmd = path_to_tesseract
        #Open image with PIL
        img = Image.open(path_to_image)
        #Extract text from image
        text = pytesseract.image_to_string(img)


        for cont in range(3):
            for t in text:
                
                if(t==":" or t=="."):
                    cant=(mision.__len__())-1
                    seguir=False
            
                else:
                    seguir=True
                
                if(seguir==True):
                    if(isNumeric(t)):
                        i=i+t
                    else:
                        if(mision.count(i)==0 and t!='' ):
                            mision.append(i)
                        
                        i=""
            mision=' '.join(mision).split() # quitamos huecos en blanco
            print(mision)

            if(mision.__len__()<0):
                conseguido=False

            misiones.append(mision)
            mision=[]
            if(misiones.__len__()<1):
                print("a")
                if( misiones[1].__len__()==0):
                    pg.moveTo(1570, 520)
                    print("he ido a la segunda pestaña")
                elif( misiones[2].__len__()==0):
                    pg.moveTo(1915, 493)
                pg.click()

            im2 = pg.screenshot(region=(1160, 869, 715, 62))
            im2.save(path_to_image)
            pytesseract.tesseract_cmd = path_to_tesseract
            img = Image.open(path_to_image)
            text = pytesseract.image_to_string(img)


        
    
        
    for mis in misiones:
        tie=int(mis[2])
        exp=int(mis[1])
        oro=int(mis[0])
        if(Todo==True):
            oxe=oro*exp/tie
        else:
            oxe=exp/tie
        print(oxe) 
        medias.append(oxe)


    if(medias[0]>medias[1]):
        if(medias[0]>medias[2]):
            buena=0
        else:
            buena=2
    else:
        if(medias[1]>medias[2]):
            buena=1
        else:
            buena=2
    if(buena==0):
        pg.moveTo(1209, 486)
        pg.click()
    elif(buena==1):
        pg.moveTo(1570, 520)
        pg.click()
    elif(buena==2):
        pg.moveTo(1915, 493)
        pg.click()

    
    pg.moveTo(1570, 1022)
    pg.click()
    
    tie=int(misiones[buena][2])
    tiempoTotal=(tiempo(str(tie)))
    print(str(tiempoTotal))
    time.sleep(tiempoTotal)
    pg.moveTo(1556, 1299)
    pg.click()
    pg.click()
   