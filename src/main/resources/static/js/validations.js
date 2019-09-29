function validaCategoria(urlDestino, form) {
    $('#formCategoria').validate({
        rules:{
            descricao: {
                required: true
            }
        },
        messages: {
            descricao: {
                required: "Preenchimento obrigatório!"
            }
        },
        submitHandler: function (frm) {
            return save(urlDestino, form);
        }
    });
}

function validaMarca(urlDestino, form) {
    $('#formMarca').validate({
        rules:{
            descricao: {
                required: true
            }
        },
        messages: {
            descricao: {
                required: "Preenchimento obrigatório!"
            }
        },
        submitHandler: function (frm) {
            return save(urlDestino, form);
        }
    });
}

function validaProduto(urlDestino, form) {
    $('#formProduto').validate({
        rules:{
            nome: {
                required: true
            },
            precoCusto: {
                required: true
            },
            precoVenda: {
                required: true
            },
            categoria: {
                required: true
            },
            marca: {
                required: true
            }

        },
        messages: {
            nome: {
                required: "Preenchimento obrigatório!"
            },
            precoCusto: {
                required: "Preenchimento obrigatório!"
            },
            precoVenda: {
                required: "Preenchimento obrigatório!"
            },
            categoria: {
                required: "Preenchimento obrigatório!"
            },
            marca: {
                required: "Preenchimento obrigatório!"
            }
        },
        submitHandler: function (frm) {
            return save(urlDestino, form);
        }
    });
}

function validaFornecedor(urlDestino, form) {
    $('#formFornecedor').validate({
        rules:{
            razaoSocial: {
                required: true
            },
            nomeFantasia: {
                required: true
            },
            cnpj: {
                required: true
            },
            ie: {
                required: true
            },
            cidade: {
                required: true
            },
            estado: {
                required: true
            },
            dataFundacao: {
                required: true
            }

        },
        messages: {
            razaoSocial: {
                required: "Preenchimento obrigatório!"
            },
            nomeFantasia: {
                required: "Preenchimento obrigatório!"
            },
            cnpj: {
                required: "Preenchimento obrigatório!"
            },
            ie: {
                required: "Preenchimento obrigatório!"
            },
            cidade: {
                required: "Preenchimento obrigatório!"
            },
            estado: {
                required: "Preenchimento obrigatório!"
            },
            dataFundacao: {
                required: "Preenchimento obrigatório!"
            }
        },
        submitHandler: function (frm) {
            return save(urlDestino, form);
        }
    });
}