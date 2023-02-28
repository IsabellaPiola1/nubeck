# nubeck

## Endpoints

- [Cadastro de despesa](#cadastro-de-despesa)
- [Detalhar despesas](#detalhar-despesas)
- Lista de despesas
- Apagar despesa
- Editar despesa

### Cadastro de despesa

POST nubeck/api/v1/despesa

**Exemplo de Entrada**

```js
{
    "valor": 100,
    "categoria_id": 'Lazer',
    "conta_id": 'itaú',
    "data": '2023-01-01',
    "descricao": 'cinema'

}
```
**Campos da Requisição**
| Campo | Obrigatório | Tipo  | Descrição |
|-------|:-------------:|-------|-----------|
|valor  |sim          |decimal|O valor da despesa, deve ser maior que zero
|categoria_id|sim|int| O id da categoria da despesa, deve ser uma categoria previamente cadastrada
|conta_id|sim|int| O id da conta da despesa, deve ser uma conta previamente cadastrada
|data|sim|data| a data da despesa
|descricao|não|texto| uma descrição da despesa com no máximo 255 caracteres

**Código de Requisição**

|código|descrição
|-|-
201 | a despesa foi cadastrada com sucesso
400 | os dados enviados são invalidos

---

...

### Detalhar Despesas

`GET` nubeck/api/v1/despesa/{id}


**Exemplo de Resposta**

```js
{
    "valor": 100,
    "categoria_id": 1{
        "id": 1,
        "nome": 'lazer'
    }
    "conta": {
        "id": 1,
        "nome": 'santander'
    }
    "data": '2023-01-01',
    "descricao": 'cinema'
}
```


**Código de Requisição**

|código|descrição
|-|-
200 | os dados da despesa foram retornados
404 | não existe despesa  com o ID 
