$(document).ready(main);

//Función ejecutada al cargar la página
function main () 
{
    //Inicializar elementos al cargar la página
    $("#seccionDatosEmpresa").hide();
    $("#TRBtnCrear").hide();
    $("#TRBtnEliminar").hide();
    $("#TRBtnModificar").hide();
    $("#TRBtnCancelar").hide();
    $("#btnCrear").button();
    $("#btnEliminar").button();
    $("#btnModificar").button();
    $("#btnCancelar").button();
    
    //Cargar información inicial de la pagina
    $.ajax({
    type: "POST",
    url: "../empresaservlet",
    data: {
        "accion": 0
    },
    cache: false,
    async: false,
    dataType: 'json',
    success: function(data) 
    {
        if(data.e=="0"){
            //Llenar combo tipo identificacion
            $.each(data.tipoIds, function (i, item) 
            {
                $('#documentoSelect').append($('<option>', {
                    value: item.abreviatura,
                    text: item.abreviatura + " - " + item.descripcion
                }));
            });
        } 
        else 
        {
            dialogGenerico("Error", "Error al consultar los datos iniciales de la página: " + data.msg);
        }
    }, error: function(data){
            dialogGenerico("Error", "Error no determinado al consultar los datos iniciales de la página");
        }
    });
	
    $("#btnCancelar").click(function () {
        //Ocultar todos los controles
        $("#seccionDatosEmpresa").hide();
        $("#TRBtnCrear").hide();
        $("#TRBtnEliminar").hide();
        $("#TRBtnModificar").hide();
        $("#TRBtnCancelar").hide();
        
        //Limpiar formulario para la captura de empleados
        $(".infoEmpresa").val("");
        $("#numeroIdEmpresa").val("");
        $("#documentosSelect").val("");
    });
    
}

function validarCamposObligatorios(nombreDiv, inputsNoObligatorios) {
    var div = nombreDiv;
    var obligatorio = $('#' + div + ' :input');
    camposCompletos = true;
    var customValidate = true;
    $.each(obligatorio, function () {
        if ($.inArray($(this).attr('id'), inputsNoObligatorios) ==  - 1  && !$(this).hasClass('search_init') && $(this).attr("type") != "search") {
            $("#Div" + $(this).attr("id")).remove();
            if ($(this).is(":visible") == true && ($(this).val() == '' || $(this).val() == '-1' || $(this).val() == null || $(this).val() == '0')) {
                $(this).parent().append("<div id=\"Div" + $(this).attr("id") + "\" style=\"color: red;\">Campo Obligatorio<div>");
                highlightElement($(this));
                camposCompletos = false;
            }
        }
    });
    return camposCompletos;
}

function highlightElement(element) {
    element.effect('highlight',{ color : '#FFB1B0' }, 200, function () {
        element.effect('highlight',{ color : '#FFB1B0'}, 200, function () {
            element.effect('highlight',{ color : '#FFB1B0' }, 200, function () {
                element.effect('highlight',{ color : '#FFB1B0'}, 200, function () {
                    element.effect('highlight', { color : '#FFB1B0' }, 200);
                });
            });
        });
    });
}