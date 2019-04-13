$(document).ready(function() {
    console.log("ready on");
    $('div').addClass('sortable');
    $('.sortable').on('mousedown', function() {
        $(this).addClass('draggable').removeClass('sortable');
        startMove();
    }).on('mouseup', function() {
        $(this).removeClass('draggable').addClass('sortable');
        endMove();
    });
});

function startMove() {
    $('.draggable').on('mousemove', function(event) {
        console.log('THERE ARE DRAGGABLES!!!!');
        var thisY = event.pageY - $(this).height() / 2;
        $('.draggable').offset({
            top: thisY
        });
        console.log("POSITION: " + thisY);
        changeOtherDivPosition(thisY);
    });
}

function endMove() {
    console.log("end move");
    $(this).removeClass('draggable');
}

function changeOtherDivPosition(draggableY) {
    $('.sortable').each(function( index, element ) {
        var otherY = $('.sortable').position().top;
        var newY = getNewPosition(otherY);
        if (otherY < draggableY) {
            console.log("change others " + otherY + " " + draggableY + " " + newY);
            $(element).offset({
                top: newY
            });
            console.log("changed position of: " + index);
            return false;
        }
    })
}

function getNewPosition(position) {
    var positions = [5, 50, 95, 140, 185, 230], newPosition = 5;
    for(var i = positions.length - 1; i > 0; i --) {
        if(position > positions[i]) {
            newPosition = positions[i];
        }
    }
    console.log("getNewPosition: " + position + " -> " + newPosition);
    return newPosition;
}