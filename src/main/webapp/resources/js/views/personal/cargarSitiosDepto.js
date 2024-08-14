var ProcessStorage = function () {

    return {
        //main function to initiate the module
        init: function (parametros) {

            var isRtl = $('html').attr('dir') === 'rtl';



            // Select2
         /*   $(function() {
                $('.select2-control').each(function() {
                    $(this)
                        .select2({
                            placeholder: parametros.seleccionar,
                            dropdownParent: $(this).parent(),
                            language:parametros.lenguaje
                        });
                })
            });*/

            $.validator.setDefaults( {
                submitHandler: function () {
                    processEntity();
                }
            } );
            jQuery.validator.addMethod("noSpace", function(value, element) {
                return value.indexOf(" ") < 0 && value != "";
            }, "Invalid");
            $( '#search-form' ).validate( {
                rules: {
                },
                // Errors
                //

                errorPlacement: function errorPlacement(error, element) {
                    var $parent = $(element).parents('.form-group');

                    // Do not duplicate errors
                    if ($parent.find('.jquery-validation-error').length) { return; }

                    $parent.append(
                        error.addClass('jquery-validation-error small form-text invalid-feedback')
                    );
                },
                highlight: function(element) {
                    var $el = $(element);
                    var $parent = $el.parents('.form-group');

                    $el.addClass('is-invalid');

                    // Select2 and Tagsinput
                    if ($el.hasClass('select2-hidden-accessible') || $el.attr('data-role') === 'tagsinput') {
                        $el.parent().addClass('is-invalid');
                    }
                },
                unhighlight: function(element) {
                    $(element).parents('.form-group').find('.is-invalid').removeClass('is-invalid');
                }
            });

            $('#sitio').change(
                function() {
                    $.blockUI({ message: parametros.waitmessage });
                    $(".masonry-grid").empty();
                    $.getJSON(parametros.getSitiosICS, {
                        //equipId : $('#equip').val(),
                        ajax : 'true'
                    }, function(data) {
                        var html='<option value=""></option>';
                        var len = data.length;
                        for ( var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].id + '">'
                                + data[i].descripcion + '</option>';
                        }
                        $('#depto').html(html);
                        $('#depto').focus();
                      // $('#depto').select2('open');
                    });
                    $.unblockUI();
                });

            $('#depto').change(
                function() {
                    $.blockUI({ message: parametros.waitmessage });
                    $(".masonry-grid").empty();
                    $.getJSON(parametros.getDepartamentosICS, {
                        id : $('#depto').val(),
                        ajax : 'true'
                    }, function(data) {
                        var html='<option value=""></option>';
                        var len = data.length;
                        for ( var i = 0; i < len; i++) {
                            html += '<option value="' + data[i].id + '">'
                                + data[i].descripcion + '</option>';
                        }

                    });
                    $.unblockUI();
                });


            $('#boxSpecId').change(
                function() {
                    $.blockUI({ message: parametros.waitmessage });
                    $.getJSON(parametros.boxUrl, {
                        boxId : $('#boxSpecId').val(),
                        ajax : 'true'
                    }, function(data) {
                        var item;
                        $('.masonry-grid').empty();
                        for (var i = 1; i <= data.box.capacity; i++) {
                            item = "<div class='masonry-grid-item'><p class='pos'>"+i+"" +"</p>" +
                                "<p class='ident'><button type='button' data-toggle='modal' " +
                                " id='bttn" + i + "' onclick='$(\"#position\").val(" + i + ")' data-target='#modals-default' class='btn rounded-pill btn-outline-primary'><i class='fa fa-plus'></i></button></p>";
                            for(var j = 0; j < data.aliquots.length; j++){
                                if(data.aliquots[j].pos == i){
                                    var viewUrl = parametros.listUrl  + data.aliquots[j].specimen.systemId+'/';
                                    item = "<div class='masonry-grid-item'><p class='pos'>"+i+"</p>";
                                    item += "<p class='ident'><a href='"+ viewUrl +"'>"+ data.aliquots[j].specimen.specimenId +"</a></p>";
                                    item += "<p class='tipo'>"+ data.aliquots[j].specimen.specimenType +"</p>";
                                }
                            }
                            item += "</div>";
                            $(".masonry-grid").append(item);
                        }
                        var ancho = 100 / data.box.columns + '%';

                        $('.masonry-grid-item').css({"width":ancho});
                        $('.masonry-grid-item').css({"position": "relative"});
                        $('.masonry-grid-item').css({"float": "left"});
                        $('.masonry-grid-item').css({"height": "100px"});


                        $('.masonry-grid').masonry({
                            itemSelector: '.masonry-grid-item',
                            percentPosition: true
                        });
                        $.unblockUI();
                    });
                });



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


            $('#modals-default').on('shown.bs.modal', function() {

                $('input:text:visible:first').focus();
            });

        }
    };
}();
