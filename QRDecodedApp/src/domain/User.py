'''
Created on 10/11/2011

@author: gustavo
'''
from time import *

class User(object):
    '''  Clase que modela un usuario registrado por intermedio de codigo QR '''


    def __init__(self,token):
        '''Constructor'''
        self.name = token.split("-")[0]
        self.surname = token.split("-")[1]
        self.doc = token.split("-")[2]
        
        #Tipo funcionario docente-no docente
        self.funcType = token.split("-")[3]
        
        #Si es docente, materia que dicta, si
        #no es docente, funcion que desempenia
        self.charge = token.split("-")[4]
        
        #Fecha y hora que se realizo la lectura
        self.captureTime = strftime("%d/%m/%Y %H:%M:%S")
        
        