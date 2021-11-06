/**
 * MyNotificationBar Widget (based on Primefaces NotificarionBar)
 */
PrimeFaces.widget.MyNotificationBar = PrimeFaces.widget.BaseWidget.extend({

    init: function(cfg) {
        this._super(cfg);
        this.closeIcon = this.jq.children('.ui-dialog-notificationbar-close');
        this.minimizeIcon = this.jq.children('.ui-dialog-notificationbar-minimize');
        this.maximizeIcon = this.jq.children('.ui-dialog-notificationbar-maximize');

        //docking zone
        if($(document.body).children('.ui-notificationbar-docking-zone').length === 0) {
            $(document.body).append('<div class="ui-notificationbar-docking-zone"></div>');
        }

        //relocate
        this.jq.css(this.cfg.position, '0').appendTo($('body'));

        //display initially
        if(this.cfg.autoDisplay) {
            $(this.jq).css('display','block')
        }

        //events
        this.bindEvents();//bind events

    },

    /**
     * The up-to-three arguments will be routed to jQuery as it is.
     * @see: http://api.jquery.com/slidedown/
     * @see: http://api.jquery.com/fadein/
     * @see: http://api.jquery.com/show/
     */
    show: function(a1, a2, a3) {
        if(this.cfg.effect === 'slide')
            $(this.jq).slideDown(a1, a2, a3);
        else if(this.cfg.effect === 'fade')
            $(this.jq).fadeIn(a1, a2, a3);
        else if(this.cfg.effect === 'none')
            $(this.jq).show(a1, a2, a3);
    },

    hide: function() {
        if(this.cfg.effect === 'slide')
            $(this.jq).slideUp(this.cfg.effect);
        else if(this.cfg.effect === 'fade')
            $(this.jq).fadeOut(this.cfg.effect);
        else if(this.cfg.effect === 'none')
            $(this.jq).hide();
    },

    bindEvents: function() {
        let $this = this;
        this.closeIcon.on('click', function(e) {
            $this.hide();
            e.preventDefault();
        });
        this.maximizeIcon.click(function(e) {
            $this.toggleMaximize();
            e.preventDefault();
        });

        this.minimizeIcon.click(function(e) {
            $this.toggleMinimize();
            e.preventDefault();
        });
    },

    toggleMinimize: function() {
        let dockingZone = $(document.body).children('.ui-notificationbar-docking-zone');

        if(this.maximized) {
            this.toggleMaximize();
        }

        if(this.minimized) {
            this.removeMinimize();
        } else {
            this.saveState();
            this.dock(dockingZone);
            this.jq.addClass('ui-dialog-minimized');
        }
    },

    removeMinimize: function() {
        this.jq.appendTo(this.parent).removeClass('ui-dialog-minimized').css({'position':'fixed', 'float':'none'});
        this.restoreState();
        this.minimizeIcon.removeClass('ui-state-hover').children('.ui-icon').removeClass('ui-icon-plus').addClass('ui-icon-minus');
        this.minimized = false;

    },

    toggleMaximize: function() {
        if(this.minimized) {
            this.toggleMinimize();
        }

        if(this.maximized) {
            this.jq.removeClass('ui-dialog-maximized');
            this.restoreState();

            this.maximizeIcon.children('.ui-icon').removeClass('ui-icon-newwin').addClass('ui-icon-extlink');
            this.maximized = false;

        }
        else {
            this.saveState();

            let win = $(window);

            this.jq.addClass('ui-dialog-maximized').css({
                'width': win.width() - 6
                ,'height': win.height()
            }).offset({
                top: win.scrollTop()
                ,left: win.scrollLeft()
            });

            this.maximizeIcon.removeClass('ui-state-hover').children('.ui-icon').removeClass('ui-icon-extlink').addClass('ui-icon-newwin');
            this.maximized = true;

        }
    },

    saveState: function() {
        this.state = {
            width: this.jq.width(),
            height: this.jq.height()
        };

        let win = $(window);
        this.state.offset = this.jq.offset();
        this.state.windowScrollLeft = win.scrollLeft();
        this.state.windowScrollTop = win.scrollTop();
    },

    restoreState: function() {
        this.jq.width(this.state.width).height(this.state.height);

        let win = $(window);
        this.jq.offset({
            top: this.state.offset.top + (win.scrollTop() - this.state.windowScrollTop)
            ,left: this.state.offset.left + (win.scrollLeft() - this.state.windowScrollLeft)
        });
    },

    dock: function(zone) {
        zone.css('z-index', this.jq.css('z-index'));
        this.jq.appendTo(zone).css('position', 'static');
        this.jq.css({'height':'auto', 'width':'auto', 'float': 'left'});
        this.minimizeIcon.removeClass('ui-state-hover').children('.ui-icon').removeClass('ui-icon-minus').addClass('ui-icon-plus');
        this.minimized = true;

    },

});
