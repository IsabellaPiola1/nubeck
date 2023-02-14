# nubeck

## Endpoints

- Cadastro de despesa
- Lista de despesas
- Apagar despesa
- Editar despesa

### Cadastro de despesa

POST nubeck/api/v1/despesa

**Exemplo de Entrada**

```json

{
    valor: 100,
    categoria: 'Lazer',
    conta: 'itaú',
    data: '2023-01-01,
    descricao: 'cinema'

}

```

| Campo | Obrigatório | Tipo | Decrição |
|-|-|-|-
|valor|sim|decimal| o valor da despesa
|categoria|sim|texto| A categoria da despesa,
deve ser uma categoria previamente cadastrada |