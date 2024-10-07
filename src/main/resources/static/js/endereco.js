let cepValido = false;

async function buscarCep() {
    const cep = document.getElementById('cep').value.replace(/\D/g, '');

    if (cep.length === 8) {
        try {
            const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
            const data = await response.json();

            if (!data.erro) {
                document.getElementById('rua').value = data.logradouro;
                document.getElementById('bairro').value = data.bairro;
                document.getElementById('cidade').value = data.localidade;
                document.getElementById('estado').value = data.uf;
                cepValido = true;
            } else {
                alert("CEP não encontrado!");
                limparCamposEndereco();
                cepValido = false;
            }
        } catch (error) {
            console.error("Erro ao buscar o CEP:", error);
            limparCamposEndereco();
            cepValido = false;
        }
    } else {
        alert("CEP inválido!");
        limparCamposEndereco();
        cepValido = false;
    }
}

function limparCamposEndereco() {
    document.getElementById('rua').value = '';
    document.getElementById('bairro').value = '';
    document.getElementById('cidade').value = '';
    document.getElementById('estado').value = '';
}

async function validarFormulario(event) {
    const cep = document.getElementById('cep').value.replace(/\D/g, '');

    if (cep.length === 8 && !cepValido) {
        await buscarCep();  // Espera a validação do CEP ser concluída
        if (!cepValido) {
            alert("Por favor, insira um CEP válido antes de continuar.");
            event.preventDefault();  // Impede o envio do formulário se o CEP não for válido
        }
    } else if (!cepValido) {
        alert("Por favor, insira um CEP válido antes de continuar.");
        event.preventDefault();  // Impede o envio do formulário se o CEP não for válido
    }
}

function inicializarCepHandler() {
    document.getElementById('cep').addEventListener('blur', buscarCep);
    document.querySelector('form').addEventListener('submit', validarFormulario);
}

window.addEventListener('load', inicializarCepHandler)
