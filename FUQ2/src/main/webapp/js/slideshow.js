
$(function() {
var body = $(‘body’);
var backgrounds = new Array(
‘url(../img/backgrounds/sunset.jpg)’,
‘url(../img/backgrounds/lake.jpg)’,
‘url(../img/backgrounds/forest.jpg)’,
‘url(../img/backgrounds/stream.jpg)’
);
var current = 0;

function nextBackground() {
body.css(
‘background’,
backgrounds[current = ++current % backgrounds.length]
);

setTimeout(nextBackground, 10000);
}
setTimeout(nextBackground, 10000);
body.css(‘background’, backgrounds[0]);
});