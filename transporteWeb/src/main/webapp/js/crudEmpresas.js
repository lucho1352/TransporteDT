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
    $("#btnRepLegal").hide();
    $("#btnCrear").button();
    $("#btnEliminar").button();
    $("#btnModificar").button();
    $("#btnCancelar").button();
    $("#btnRepLegal").button();
    
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
	
    $("#btnRepLegal").click(function () {
    	dialogGenerico("Info", "En esta opcion se ubica la administración del representante legal para la empresa");
    });
    
    $("#btnCancelar").click(function () {
        //Ocultar todos los controles
        $("#seccionDatosEmpresa").hide();
        $("#TRBtnCrear").hide();
        $("#TRBtnEliminar").hide();
        $("#TRBtnModificar").hide();
        $("#TRBtnCancelar").hide();
        $("#btnRepLegal").hide();
        
        //Limpiar formulario para la captura de empleados
        $(".infoEmpresa").val("");
        $("#numeroIdEmpresa").val("");
        $("#documentosSelect").val("");
    });
    
    $("#numeroIdEmpresa").focusout(function() {
        var valor = $(this).val();
        if (valor != '' && $("#documentosSelect").val() != '') {
            $.ajax({
                type: "POST",
                url: "../empresaservlet",
                data: {
                    "accion": 1,
                    "numeroID":valor,
                    "tipoID":$("#documentoSelect").val()
                },
                cache: false,
                async: false,
                dataType: 'json',
                success: function(data) 
                {
                    //Ocultar todos los controles
                    $("#seccionDatosEmpresa").hide();
                    $("#TRBtnCrear").hide();
                    $("#TRBtnEliminar").hide();
                    $("#TRBtnModificar").hide();
                    $("#TRBtnCancelar").hide();
                    
                    //Limpiar formulario para la captura de empleados
                    $(".infoEmpresa").val("");
                    
                    if (data.e == "0") 
                    {
                        //Mostrar sección y determinan botones a mostrar
                        $("#seccionDatosEmpresa").show();
                        $("#TRBtnCancelar").show();
                        if (data.existe == "1")
                        {
                            $("#TRBtnEliminar").show();
                            $("#TRBtnModificar").show();
                            $("#btnRepLegal").show();
                            
                            $("#nombreEmpresa").val(data.empresa.nombre);
                            $("#paisEmpresa").val(data.empresa.pais);
                            $("#departamentoEmpresa").val(data.empresa.departamento);
                            $("#ciudadEmpresa").val(data.empresa.ciudad);
                            $("#direccionEmpresa").val(data.empresa.direccion);
                            $("#telefonoEmpresa").val(data.empresa.telefono);
                        }
                        else
                        {
                            $("#TRBtnCrear").show();
                        }
                    }else 
                    {
                        dialogGenerico("Error", "Error al consultar empleado: " + data.msg);
                    }
                }, error: function(data) {
                    dialogGenerico("Error", "Error no determinado al consultar el empleado");
                }
            });
        }
    });
    
    $("#btnCrear").click(function () {
        if(validarCamposObligatorios("seccionDatosEmpleado",[])){
            $.ajax({
            type: "POST",
            url: "../empresaservlet",
            data: {
                "accion": 2,
                "numeroID":$("#numeroIdEmpresa").val(),
                "tipoID":$("#documentoSelect").val(),
                "pais":$("#paisEmpresa").val(),
                "ciudad":$("#ciudadEmpresa").val(),
                "departamento":$("#departamentoEmpresa").val(),
                "nombre":$("#nombreEmpresa").val(),
                "direccion":$("#direccionEmpresa").val(),
                "telefono":$("#telefonoEmpresa").val()
            },
            cache: false,
            async: false,
            dataType: 'json',
            success: function(data) 
            {
                if(data.e =="0"){
                    dialogGenerico("Informativo",data.msg);
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
                } 
                else 
                {
                    dialogGenerico("Error", "Error al crear empresa: " + data.msg);
                }
            }, error: function(data) {
                    dialogGenerico("Error", "Error no determinado al crear empresa");
                }
            });
        }
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

//Funcion para colocar un mensaje emergente en pantalla
function dialogGenerico(titulo, dato) {
    $("#mensajesPantalla").dialog({
        height: "auto",
        width: "auto",
        resizable: true
    });
    $("#mensajesPantalla").dialog('option', 'title', titulo);
    $("#mensajesPantalla").html('<center> <div style="align:center"><br>' + dato + '<\/div><\/center>');
    $("#mensajesPantalla").dialog('option', 'buttons', {
        OK: function() {
            $(this).dialog('close')
            }
        });
    $("#mensajesPantalla").dialog('open');
}