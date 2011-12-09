
function excelCallback(){


    $.ajax({

        url:"/InOutManager-war/ExcelGeneratorPopUp",
        dataType:"html",
        type:"POST",
        success:function(){
            alert('success');
        }

        
    });
}

