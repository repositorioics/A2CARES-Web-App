var ProcessPersonal = function () {
	

    return {
        //main function to initiate the module
        init: function (parametros) {

            // wrapper function to  block element(indicate loading)
            function blockUI(el, centerY) {
                var el = jQuery(el);
                var loc = window.location;
                var pathName = loc.pathname.substring(0,loc.pathname.indexOf('/', 1)+1);
                var mess = '<img src=' + pathName + 'resources/img/ajax-loading.gif align="">';
                if (el.height() <= 400) {
                    centerY = true;
                }
                el.block({
                    message: mess,
                    centerY: centerY != undefined ? centerY : true,
                    css: {
                        top: '10%',
                        border: 'none',
                        padding: '2px',
                        backgroundColor: 'none'
                    },
                    overlayCSS: {
                        backgroundColor: '#000',
                        opacity: 0.05,
                        cursor: 'wait'
                    }
                });
            }

// wrapper function to  un-block element(finish loading)
            function unblockUI (el) {
                jQuery(el).unblock({
                    onUnblock: function () {
                        jQuery(el).removeAttr("style");
                    }
                });
            }

            var form1 = $('#enter-form');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    'nombre': {
                        required: true
                    },
                    'cargo': {
                        required: true
                    }
                },
                errorPlacement: function ( error, element ) {
                    // Add the `help-block` class to the error element
                    error.addClass( 'form-control-feedback' );
                    if ( element.prop( 'type' ) === 'checkbox' ) {
                        error.insertAfter( element.parent( 'label' ) );
                    } else {
                        //error.insertAfter( element ); //cuando no es input-group
                        error.insertAfter(element.parent('.input-group'));
                    }
                },
                highlight: function ( element, errorClass, validClass ) {
                    $( element ).addClass( 'form-control-danger' ).removeClass( 'form-control-success' );
                    $( element ).parents( '.form-group' ).addClass( 'has-danger' ).removeClass( 'has-success' );
                },
                unhighlight: function (element, errorClass, validClass) {
                    $( element ).addClass( 'form-control-success' ).removeClass( 'form-control-danger' );
                    $( element ).parents( '.form-group' ).addClass( 'has-success' ).removeClass( 'has-danger' );
                },
                submitHandler: function (form) {
                    processCatalog();
                }
            });
            
            function processCatalog() {
                blockUI();
                $.post(parametros.saveUrl
                    ,  { codigo: $("#code").val(), nombre : $("#nombre").val(), cargo : $('#cargo').find('option:selected').val()}
                    , function (data) {
                        entidad = JSON.parse(data);
                        if (entidad.recordUser === undefined) {
                            toastr.error(data, parametros.errormessage,{timeOut: 0});
                        } else {
                            toastr.success(parametros.successmessage, entidad.idPersona);
                            unblockUI();
                            window.setTimeout(function () {
                                window.location.href = parametros.listUrl;
                            }, 1500);
                        }
                    }
                    , 'text')
                    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
                        //alert("error:" + errorThrown);
                        toastr.error(errorThrown, parametros.errormessage,{timeOut: 0});
                        unblockUI();
                    });
            }
            
    	    
    	    $(document).on('keypress','form input',function(event)
    		{                
    		    event.stopImmediatePropagation();
    		    if( event.which == 13 )
    		    {
    		        event.preventDefault();
    		        var $input = $('form input');
    		        if( $(this).is( $input.last() ) )
    		        {
    		            //Time to submit the form!!!!
    		            //alert( 'Hooray .....' );
    		        }
    		        else
    		        {
    		            $input.eq( $input.index( this ) + 1 ).focus();
    		        }
    		    }
    		});
        }
    };











}();