
/*$(function() {
var body = $(�body�);
var backgrounds = new Array(
�url(../img/backgrounds/sunset.jpg)�,
�url(../img/backgrounds/lake.jpg)�,
�url(../img/backgrounds/forest.jpg)�,
�url(../img/backgrounds/stream.jpg)�
);
var current = 0;

function nextBackground() {
body.css(
�background�,
backgrounds[current = ++current % backgrounds.length]
);

setTimeout(nextBackground, 10000);
}
setTimeout(nextBackground, 10000);
body.css(�background�, backgrounds[0]);
});*/

$(document).ready(function () {
    var img_array = [1, 2, 3, 4],
        newIndex = 0,
        index = 0,
        interval = 5000;
    (function changeBg() {

        //  --------------------------
        //  For random image rotation:
        //  --------------------------

              //newIndex = Math.floor(Math.random() * 10) % img_array.length;
              //index = (newIndex === index) ? newIndex -1 : newIndex;

        //  ------------------------------
        //  For sequential image rotation:
        //  ------------------------------

            index = (index + 1) % img_array.length;

        $('body').css('backgroundImage', function () {
            $('#fullPage').animate({
                backgroundColor: 'transparent'
            }, 1000, function () {
                setTimeout(function () {
                    $('#fullPage').animate({
                        backgroundColor: 'rgb(255,255,255)'
                    }, 1000);
                }, 3000);
            });
            return 'url(../img/backgrounds/b.' + img_array[index] + '.jpg)';
        });
        setTimeout(changeBg, interval);
    })();
});