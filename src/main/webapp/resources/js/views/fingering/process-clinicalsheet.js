var ClinicalSheet = function () {
	
	var handleSelect = function () {
        $("#medico").select2();
        $("#enfermeria").select2();
        $("#sexo").select2();
        $("#tipoConsulta").select2();
        $("#lugarConsutla").select2();
        $("#categoria").select2();
        $("#cambioCategoria").select2();
        $("#hospitalizado").select2();
        $("#transfusion").select2();
        $("#tomaMedicamento").select2();
    };

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

            handleSelect();
            var form1 = $('#form-clinicalsheet');
            form1.validate({
                errorElement: 'span', //default input error message container
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    fechaCons: {
                        required: true
                    },
                    horaCons: {
                        required: true
                    },
                    peso: {
                        required: true
                    },
                    rbgeneral_1 : {
                        required: true
                    },
                    rbgeneral_2 : {
                        required: true
                    },
                    rbgeneral_3 : {
                        required: true
                    },
                    rbgeneral_4 : {
                        required: true
                    },
                    rbgeneral_5 : {
                        required: true
                    },
                    rbgeneral_6 : {
                        required: true
                    },
                    rbgeneral_7 : {
                        required: true
                    },
                    rbgastroin_1 : {
                        required: true
                    },
                    rbgastroin_2 : {
                        required: true
                    },
                    rbgastroin_3 : {
                        required: true
                    },
                    rbgastroin_4 : {
                        required: true
                    },
                    rbgastroin_5 : {
                        required: true
                    },
                    rbgastroin_6 : {
                        required: true
                    },
                    rbcabeza_1 : {
                        required: true
                    },
                    rbcabeza_2 : {
                        required: true
                    },
                    rbcabeza_3 : {
                        required: true
                    },
                    rbcabeza_4 : {
                        required: true
                    },
                    rbosteomusc_1 : {
                        required: true
                    },
                    rbosteomusc_2 : {
                        required: true
                    },
                    rbosteomusc_3 : {
                        required: true
                    },
                    rbosteomusc_4 : {
                        required: true
                    },
                    rbosteomusc_5 : {
                        required: true
                    },
                    rbgarganta_1 : {
                        required: true
                    },
                    rbgarganta_2 : {
                        required: true
                    },
                    rbgarganta_3 : {
                        required: true
                    },
                    rbgarganta_4 : {
                        required: true
                    },
                    rbgarganta_5 : {
                        required: true
                    },
                    rbcutaneo_1 : {
                        required: true
                    },
                    rbcutaneo_2 : {
                        required: true
                    },
                    rbcutaneo_3 : {
                        required: true
                    },
                    rbcutaneo_4 : {
                        required: true
                    },
                    rbcutaneo_5 : {
                        required: true
                    },
                    rbcutaneo_6 : {
                        required: true
                    },
                    rbcutaneo_7 : {
                        required: true
                    },
                    rbcutaneo_8 : {
                        required: true
                    },
                    rbcutaneo_9 : {
                        required: true
                    },
                    rbrespiratorio_1 : {
                        required: true
                    },
                    rbrespiratorio_2 : {
                        required: true
                    },
                    rbrespiratorio_3 : {
                        required: true
                    },
                    rbrespiratorio_4 : {
                        required: true
                    },
                    rbrespiratorio_5 : {
                        required: true
                    },
                    rbrespiratorio_6 : {
                        required: true
                    },
                    rbrespiratorio_7 : {
                        required: true
                    },
                    rbrespiratorio_8 : {
                        required: true
                    },
                    rbrespiratorio_9 : {
                        required: true
                    },
                    rbrespiratorio_10 : {
                        required: true
                    },
                    rbrespiratorio_11 : {
                        required: true
                    },
                    rbrespiratorio_12 : {
                        required: true
                    },
                    rbestadonut_1 : {
                        required: true
                    },
                    rbestadonut_2 : {
                        required: true
                    },
                    rbestadonut_3 : {
                        required: true
                    },
                    rbestadonut_4 : {
                        required: true
                    },
                    rbestadonut_5 : {
                        required: true
                    },
                    rbestadonut_6 : {
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
                    //processUser();
                }
            });
            
            function processUser()
        	{
            	blockUI();
        	    $.post( parametros.saveUserUrl
        	            , form1.serialize()
        	            , function( data )
        	            {
        	    			usuario = JSON.parse(data);
        	    			if (usuario.username === undefined) {
        						toastr.error(data,"Error",{timeOut: 0});
        					}
        					else{
        						$('#username').val(usuario.username);
        						toastr.success(parametros.successmessage,usuario.username);
        					}
        	            	$('#completeName').focus();
        	    			unblockUI();
                            window.setTimeout(function(){
                                window.location.href = parametros.usuarioUrl;
                            }, 1500);
        	            }
        	            , 'text' )
        		  		.fail(function(XMLHttpRequest, textStatus, errorThrown) {
        		    		alert( "error:" + errorThrown);
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