
jQuery(document).ready(function($){
    jQuery('.faq_block .panel-heading').eq(0).addClass('active');
    jQuery('.faq_block .panel-heading').click(function(){
        if(jQuery(this).hasClass('active')){
            jQuery(this).removeClass('active');
        }
        else{
            jQuery('.faq_block .panel-heading').removeClass('active');
            jQuery(this).toggleClass('active');
        }
    });



    jQuery("#slide-about").owlCarousel({

      navigation : true,
      slideSpeed : 300,
      paginationSpeed : 400,
      singleItem : true

      // "singleItem:true" is a shortcut for:
      // items : 1, 
      // itemsDesktop : false,
      // itemsDesktopSmall : false,
      // itemsTablet: false,
      // itemsMobile : false

    });
          wow = new WOW({
                  boxClass:     'wow',
                  animateClass: 'animated',
                  offset:       100
                });
                wow.init();
      


    jQuery('#zo2-header-top .icon-search').click(function(){
        jQuery('.search .search-form').fadeIn('300');
        jQuery('#zo2-header-top .search .search-form .inputbox').focus().css("color","#000");
    });
        jQuery('#zo2-header-top .search-close').click(function(){
        jQuery('.search .search-form').fadeOut('300');
    });


     jQuery('#masonry').imagesLoaded(function() {
        jQuery(this).masonry({
            itemSelector: '.items-row-masonry',
            columnWidth: 20,
            isAnimated: true,
            layoutPriorities: {
                shelfOrder: 1.21
            }
        });
    });



    var owl = jQuery("#zt-logo-brand .custom");
    owl.owlCarousel({
        autoPlay: 3000,
        items : 6,
        navigation : true,
        pagination : false,
        slideSpeed : 500
    });






    var IGPbRevealObjects  = null;
    var IGPbStellarObjects = null;
    $(document).ready(function (){
        // Enable Appearing animations for elements
        if($('[data-scroll-reveal]').length) {
            if (!IGPbRevealObjects) {
                IGPbRevealObjects = new scrollReveal({
                        reset: true
                    });
            }
        }
        // Enable paralax for row container
        if($('[data-stellar-background-ratio]').length) {
            if (!IGPbStellarObjects) {
                IGPbStellarObjects = $.stellar({
                    horizontalScrolling: false,
                    verticalOffset: 40
                });
            }
        }
    });


}(jQuery));

