
function callAlert(){
    alert("success");
}

function callGritter(name,doc,markId,tienePar){
    var estadoRegistro = '';
    if(tienePar){
        estadoRegistro = 'consistente.';
    }else{
        estadoRegistro = 'inconsistente.';
    }
    
    $.gritter.add({

        title: 'Registro de '+name,
        text: 'Documento número ' + doc +  ', cuyo id de registro es ' + markId + ' y su estado es ' + estadoRegistro
       // sticky: true


    });
}
