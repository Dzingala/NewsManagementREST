document.addEventListener("DOMContentLoaded", function(event) {
    function getDate() {
        var date = new Date();
        var day = date.getDate();
        var month = date.getMonth();
        var year = date.getFullYear();

        if( day <= 9 ){
            day = '0' + day;
        }

        if( month < 9 ){
            month = '0' + ( month + 1 );
        } else {
            month =  month + 1;
        }

        return year + '-' + month + '-' + day ;
    }

    function getTime() {
        var date = new Date();
        var hours = date.getHours();
        var minutes = date.getMinutes();

        if(hours < 10){
            hours = '0' + hours;
        }

        if(minutes < 10){
            minutes = '0' + minutes;
        }

        return hours + ':' + minutes ;
    }
    var creationDate = document.getElementById('creationDate');
    creationDate.setAttribute('value', getDate()+'T'+getTime());
    creationDate.setAttribute('max', getDate()+'T'+getTime());
});