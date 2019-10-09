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
                required: true,
                cnpj: true
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
                required: "Preenchimento obrigatório!",
                cnpj: "Informe um CNPJ válido!"
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
            return saveForn(urlDestino, form);
        }
    });
}

function validaUsuario(urlDestino, form) {
    $('#formUsuario').validate({
        rules:{
            nome: {
                required: true
            },
            usuario: {
                required: true
            },
            senha: {
                required: true
            },
            confSenha: {
                required: true,
                equalTo: "#senha"
            }

        },
        messages: {
            nome: {
                required: "Preenchimento obrigatório!"
            },
            usuario: {
                required: "Preenchimento obrigatório!"
            },
            senha: {
                required: "Preenchimento obrigatório!"
            },
            confSenha: {
                required: "Preenchimento obrigatório!",
                equalTo: "A senha não corresponde com a que foi informada!"
            }
        },
        submitHandler: function (frm) {
            return save(urlDestino, form);
        }
    });
}

function validaCompra(urlDestino, form) {
    $('#formCompra').validate({
        rules:{
            descricao: {
                required: true
            },
            dataCompra: {
                required: true
            },
            fornecedor: {
                required: true
            }

        },
        messages: {
            descricao: {
                required: "Preenchimento obrigatório!"
            },
            dataCompra: {
                required: "Preenchimento obrigatório!"
            },
            fornecedor: {
                required: "Preenchimento obrigatório!"
            }
        },
        submitHandler: function (frm) {
            return saveCompra(urlDestino, form);
        }
    });
}

$.validator.addMethod("cnpj", function(value, element) {
    return this.optional(element) || /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/.test(value);
}, "Informe um CNPJ válido!");
