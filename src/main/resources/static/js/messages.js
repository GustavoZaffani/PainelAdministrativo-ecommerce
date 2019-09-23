function messageSuccess() {
    $.toast({
        heading: 'Sucesso',
        text: 'Registro salvo com sucesso!',
        position: 'top-right',
        icon: 'success',
        stack: false
    });
}

function messageError() {
    $.toast({
        heading: 'Atenção',
        text: 'Ocorreu uma falha ao salvar o registro!',
        position: 'top-right',
        icon: 'error',
        stack: false
    });
}