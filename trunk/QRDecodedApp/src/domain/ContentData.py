'''
Created on 10/11/2011

@author: gustavo
'''
from time import strftime
from xmlRpcCom.RpcClient import RpcClient

class ContentData(object):
    '''  Clase que modela un usuario registrado por intermedio de codigo QR '''


    def __init__(self,token):
        '''Constructor'''
        self.codType = token.split(":")[0]
        tokenAux = token.split(":")[1]
        self.name = tokenAux.split("-")[0]
        self.surname = tokenAux.split("-")[1]
        self.doc = tokenAux.split("-")[2]
        
        #Tipo funcionario docente-no docente
        self.funcType = tokenAux.split("-")[3]
        
        #Si es docente, materia que dicta, si
        #no es docente, funcion que desempenia
        self.charge = tokenAux.split("-")[4]
        
        #Fecha y hora que se realizo la lectura
        self.captureTime = strftime("%d/%m/%Y %H:%M:%S")
        
        #envio los datos al servidor java
        self.__buildSendToken__()
        
        
    def __buildSendToken__(self):
        data = self.doc + "," + self.captureTime + "," + self.codType
        rpc = RpcClient()
        rpc.send(data)
        
        
        
        
        
    