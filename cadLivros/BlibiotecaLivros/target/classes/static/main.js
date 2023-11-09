document.getElementById('cadastroForm').addEventListener('submit', cadastrarLivros);
var result = 0;
function cadastrarLivros(event) {
    event.preventDefault();

    const isbn = document.getElementById('isbn').value;
    const descricao = document.getElementById('descricao').value;

    fetch('http://localhost:8080/livros', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ descricao, isbn }),
    })
        .then(response => response.json())
        .then(data => {
            alert('Livro cadastrado com sucesso!');
            document.getElementById('cadastroForm').reset();            
        })
        .catch(error => {
            console.error('Erro ao cadastrar livro:', error);
        });
}
function pesquisarLivro() {
    const searchId = document.getElementById('searchId').value;

    fetch(`http://localhost:8080/livros/${searchId}`)
        .then(response => {
            if (response.status === 404) {
                return Promise.reject('Jogo não encontrado');
                result = 0;
            }
            return response.json();
        })
        .then(data => {
            result = 1;
            document.getElementById('descricao').value = `${data.descricao}`;
            document.getElementById('isbn').value = `${data.isbn}`;
        })
        .catch(error => {
            console.error('Erro ao pesquisar jogo:', error);
            const resultadoPesquisa = document.getElementById('resultadoPesquisa');
            resultadoPesquisa.innerHTML = 'Jogo não encontrado.';

        });
}
function atualizarLivros() {
    pesquisarLivro();
    if (result == 1) {
        const descricao = document.getElementById('descricao').value;
        const isbn = document.getElementById('isbn').value;
        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/livros/${searchId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ descricao, isbn }),
        })
            .then(response => response.json())
            .then(data => {
                alert('Jogo atualizado com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao atualizar jogo:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum jogo foi alterado. Favor pesquisar jogo a ser alterado!');
    }
}

function deletarLivro() {
    pesquisarLivro();
    if (result == 1) {
        const descricao = document.getElementById('descricao').value;
        const isbn = document.getElementById('isbn').value;
        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/livros/${searchId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ descricao, isbn }),
        })
            .then(response => response.json())
            .then(data => {
                alert('Jogo deletado com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao deletar jogo:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum jogo foi deletado. Favor pesquisar jogo para ser deletado!');
    }
}

