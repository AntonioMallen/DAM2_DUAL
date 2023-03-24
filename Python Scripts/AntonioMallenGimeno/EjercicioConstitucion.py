from PyPDF2 import PdfFileReader
import json

pasarJson={}

pasarJson['titulo']='ConstitucionCASTELLANO.pdf'
file = open('ConstitucionCASTELLANO.pdf', 'rb')
fileReader = PdfFileReader(file)
paginas=fileReader.numPages


# Numero de palabras en el documento
contador=0
for pag in range(paginas):
    pageObj = fileReader.getPage(pag)
    text=pageObj.extract_text()
    numPal = len(text.split())
    contador=contador+numPal

print("Hay " + str(contador) + " palabras.")


# Numero de palabras distintas
TodasPalMal=[]
for pag in range(paginas):
    pageObj = fileReader.getPage(pag)
    text=pageObj.extract_text()
    aa=text.split()
    TodasPalMal.extend(aa)

TodasPal=[]
for pal in TodasPalMal: # Esto se hace para eliminar caracteres raros como "......"
    if str(pal).isalpha():
        pal=pal.lower()
        TodasPal.append(pal)

pasarJson['Numero de palabras']=len(TodasPal)

PalabrasDistintas=set(TodasPal)
print("Hay "+str(len(PalabrasDistintas))+" Palabras distintas")

# Para almacenar las palabras en una String para el json recorreremos PalabrasDistintas
palabrasdisjson=""
for palabra in PalabrasDistintas:
    palabrasdisjson=palabrasdisjson+" "+palabra


pasarJson["Palabras distintas"]=palabrasdisjson

#Longitud media 
contadorlong=0
for palabra in TodasPal:
    contadorlong=contadorlong+len(palabra)
print("El tamaño medio es "+str(contadorlong/len(TodasPal)))

## Frecuencia de las letras

def cuentaLetras(letra1):
    contadorLetra=0
    for palabra in TodasPal:
        for letra in palabra:
            if letra== letra1:
                contadorLetra=contadorLetra+1
    return contadorLetra

abecedario=['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm','ñ', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']




## Frecuencia de cada palabra
palabrasUsadasJson=""
def cuentaPalabras(palabra):
    ContadorPal1=0
    for cont in range(len(TodasPal)):
        if(palabra==TodasPal[cont]):
            ContadorPal1=ContadorPal1+1
    return ContadorPal1

for palabra in PalabrasDistintas:
    palabrasUsadasJson=palabrasUsadasJson+("La palabra "+ palabra+" esta "+str(cuentaPalabras(palabra))+" veces"+ " Y el porcentaje es "+str((cuentaPalabras(palabra))/(len(TodasPal))*100)+"%"+'\n')
pasarJson['Palabras usadas']=palabrasUsadasJson

letrasUsadas=""
for i in range(len(abecedario)):
    letrasUsadas=letrasUsadas+("La letra "+ abecedario[i]+" esta "+str(cuentaLetras(abecedario[i]))+" veces"+ " Y el porcentaje es "+str(cuentaLetras(abecedario[i])/contadorlong*100)+"%"+'\n')

pasarJson['Letras mas usadas']=letrasUsadas

with open('myJson.json', 'w') as file:
    json.dump(pasarJson, file, indent=4)


file.close