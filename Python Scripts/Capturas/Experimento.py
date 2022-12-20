import pyautogui as pg
import time
from PIL import Image
from pytesseract import pytesseract
path_to_image = 'my_screenshot.png'
time.sleep(2)
im2 = pg.screenshot(path_to_image)
path_to_tesseract = r'C:/Program Files/Tesseract-OCR/tesseract.exe'
#Define path to image

#Point tessaract_cmd to tessaract.exe
pytesseract.tesseract_cmd = path_to_tesseract
#Open image with PIL
img = Image.open(path_to_image)
#Extract text from image
text = pytesseract.image_to_string(img)
print(text)