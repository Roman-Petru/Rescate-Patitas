$(document).ready(() => {
    if(window.location.hash == '#success')
    {
        showMessageExtra("Usuario creado con éxito!", "", 500);
        window.location.hash = '';
    }
});
