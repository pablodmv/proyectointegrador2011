'''
Created on 10/11/2011

@author: gustavo
'''
import xmlrpclib
from pprint import pprint
from log.Logger import Logger

class RpcClient(object):
	'''classdocs '''


	def __init__(self):
		'''Constructor'''

	def send(self,data):
		try:
			proxy = xmlrpclib.Server('http://localhost:8000/xmlrpc')
			
			#Intento mandar los datos que quedaron pendientes por errores de conectividad
			f = open("/home/gustavo/workspace/QRDecodedApp/store/store.txt")
			#Leo el archivo y agrego cada linea del archivo a una lista
			l = []
			while True:
				line = f.readline()
				if not line: break
				l.append(line)
				#pprint(proxy.Interpreter.readData(line))
			f.close()
			
			countLine = 0
			for i in l:
				pprint(proxy.Interpreter.readData(i))
				del l[0]
				countLine = countLine + 1
			
			f = open("/home/gustavo/workspace/QRDecodedApp/store/store.txt","w")
			auxCount = 0
			while auxCount < countLine:
				f.write("")
				auxCount = auxCount +1
					
			#Luego mando el dato mas nuevo capturado
			pprint(proxy.Interpreter.readData(data))
		except:
			Logger.info("Error de conexion al servidor")
			f = open("/home/gustavo/workspace/QRDecodedApp/store/store.txt","a")
			f.write(data + "\n")
			f.close()
			
	def __oldData__(self, proxy):		
		f = open("/home/gustavo/workspace/QRDecodedApp/store/store.txt")
		while True:
			line = f.readline()
			if not line: break
			pprint(proxy.Interpreter.readData(line))
			
		f.close()
		