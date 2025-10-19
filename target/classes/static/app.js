const API_BASE_URL = 'http://localhost:8080';

const formTransacao = document.getElementById('form-transacao');
const inputValor = document.getElementById('valor');
const btnLimpar = document.getElementById('btn-limpar');

formTransacao.addEventListener('submit', async (event) => {
    // previne o comportamento padrão do formulário de recarregar a página
    event.preventDefault();

    const valor = inputValor.value;
    if (!valor) {
        alert('Por favor, insira um valor.');
        return;
    }

    const transacao = {
        valor: parseFloat(valor),
        dataHora: new Date().toISOString()
    };

    try {
        const response = await fetch(`${API_BASE_URL}/transacao`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(transacao)
        });

        if (response.status === 201) {
            formTransacao.reset(); // Limpa o formulário
            atualizarEstatisticas(); // Chama a atualização imediatamente após o sucesso
        } else {
            alert('Erro ao criar transação. Verifique o valor e tente novamente.');
        }
    } catch (error) {
        console.error('Falha na comunicação com a API:', error);
        alert('Não foi possível conectar à API.');
    }
});

btnLimpar.addEventListener('click', async () => {
    if (confirm('Tem certeza que deseja apagar TODAS as transações?')) {
        try {
            const response = await fetch(`${API_BASE_URL}/transacao`, { method: 'DELETE' });
            if(response.ok) {
                alert('Transações apagadas com sucesso!');
                atualizarEstatisticas();
            }
        } catch (error) {
            console.error('Falha na comunicação com a API:', error);
            alert('Não foi possível conectar à API.');
        }
    }
});

async function atualizarEstatisticas() {
    try {
        const response = await fetch(`${API_BASE_URL}/estatistica`);
        const stats = await response.json();

        document.getElementById('count').textContent = stats.count;
        document.getElementById('sum').textContent = stats.sum.toFixed(2).replace('.', ',');
        document.getElementById('avg').textContent = stats.avg.toFixed(2).replace('.', ',');
        document.getElementById('min').textContent = stats.min.toFixed(2).replace('.', ',');
        document.getElementById('max').textContent = stats.max.toFixed(2).replace('.', ',');
    } catch (error) {
        console.error('Falha ao buscar estatísticas:', error);
    }
}


setInterval(atualizarEstatisticas, 2000);

window.onload = atualizarEstatisticas;