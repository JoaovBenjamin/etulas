https://www.youtube.com/watch?v=ULhxvNu56h0&feature=youtu.be

# Projeto Etulas

API Rest do projeto Etulas- Vizualização de lotação de Hospital

## Divisão das resposabilidades e conograma

https://trello.com/b/kqlZiZMV/plusoft

## Requisitos

- [ ] CRUD de Hospital
- [ ] CRUD de Paciente
- [ ] CRUD de Anamnesia
- [ ] CRUD de Convenio
- [ ] CRUD de Ficha de Atendimento
- [ ] CRUD de Especialidades
- [ ] CRUD de Equipamentos
- [ ] CRUD de Sala
- [ ] CRUD de Endereço

## Documentação

### Endpoints

- [Listar Hospital](#listar-hospital)
- [Cadastrar Hospital](#cadastrar-hospital)
- [Apagar Hospital](#apagar-hospital)
- [Detalhar Hospital](#detalhar-hospital)
- [Atualizar Hospital ](#atualizar-hospital)
- [Listar Paciente](#listar-paciente)
- [Cadastrar Paciente](#cadastrar-produto)
- [Apagar  Paciente](#apagar-paciente)
- [Detalhar Paciente](#detalhar-paciente)
- [Atualizar Paciente](#atualizar-paciente)
- [Listar Anamnesia](#listar-anamnesia)
- [Cadastrar Anamnesia](#cadastrar-anamnesia)
- [Apagar  Anamnesia](#apagar-anamnesia)
- [Detalhar  Anamnesia](#detalhar-anamnesia)
- [Atualizar  Anamnesia](#atualizar-anamnesia)
- [Listar Convenio](#listar-convenio)
- [Lançar Convenio](#lancar-convenio)
- [Atualizar Convenio](#atualizar-convenio)
- [Deletar Convenio](#deletar-convenio)
- [Detalhar Convenio](#detalhar-convenio)
- [Listar Ficha Atendimento](#listar-ficha-atendimento)

### Listar Hospital

`GET` /hospital

Retorna um array com todos Hospitais cadastrados.

### Exemplo de Resposta

```js
    [
         {
        "id": 1,
        "nome": "Hospital Universitário",
        "telefone": "66554433221",
        "cnpj": "05.388.218/0001-89",
        "ativo": true
    }
    ]
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Hospital retornadas com sucesso
---

### Cadastrar Hospital

`POST` /hospital

Cadastra um hospital com os dados enviados no corpo da requisição.

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✔|Nome do hospital
|cnpj|string|✔|cnpj do hospital
|telefone|string|✔|telefone do hospital
|ativo|boolean|✔| Se o hospital está ativo ou não

```js
    {
        "nome": "Hospital Universitário",
        "telefone": "66554433221",
        "cnpj": "05.388.218/0001-89",
        "ativo": true
    }
```
### Exemplo de Resposta

```js
 {
        "id": 1,
        "nome": "Hospital Universitário",
        "telefone": "66554433221",
        "cnpj": "05.388.218/0001-89",
        "ativo": true
    }
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|201| Hospital cadastrado com sucesso|
|400| Validação falhou. Verifique os dados enviados da requisição|

---

### Apagar Hospital

`DELETE` /hospital/`{id}`

Apaga o hospital com o `id` informado no parametro do path

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Hospital apagado com sucesso|
|404| O hospital não foi encontrada, Verifique o `id` informado|
---

### Detalhar Hospital

`GET` /hospital/`{id}`

Retorna os dados do hospital com o `id` informado no parametro do path

### Exemplo de Resposta

```js

 {
        "id": 1,
        "nome": "Hospital Universitário",
        "telefone": "66554433221",
        "cnpj": "05.388.218/0001-89",
        "ativo": true
    }
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Hospital retornado com sucesso
|404| Não existe hospital com o `id` informado
---

### Atualizar Hospital

`PUT` /hospital/`{id}`

Atualiza os dados do hospital com o `id` informado no path

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅|Nome do hospital
|telefone|string|✅| Numero do telefone do hospital
|cnpj|string|✅|Numero do cnpj do hospital
|ativo|boolean|✅|Se o hospital está ativo ou não


```js
 {
        "id": 1,
        "nome": "Hospital Universitário",
        "telefone": "66554433221",
        "cnpj": "05.388.218/0001-89",
        "ativo": true
    }
```
### Exemplo de Resposta

```js
 {
        "id": 1,
        "nome": "Hospital Universitário",
        "telefone": "66554433221",
        "cnpj": "05.388.218/0001-89",
        "ativo": true
    }
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Hospital cadastrado com sucesso
|400| Validação falhou. Verifiqye os dados enviados da requisição
|404| Não existe hospital com o `id` informado

---

### Listar Paciente

`GET` /paciente

Retorna um array com todas os pacientes cadastrados em determindo hospital.
### Exemplo de Resposta

```js
    [
         {
        "id": 1,
        "nome": "Maria Oliveira",
        "cpf": "699.172.060-78",
        "telefone": "99887766551",
        "idade": 25,
        "genero": "FEMININO"
    }
    ]
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Pacientes retornados com sucesso

---

### Cadastrar Paciente

`POST` /paciente

Cadastra um paciente em um hospital de acordo com os dados enviados no corpo da requisição.

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅| Nome do paciente|
|cpf|string|✅|cpf do paciente|
|telefone|string|✅|telefone paciente|
|idade|int|✅|idade do paciente|
|genero|string|✅|MASCULINO ou FEMININO



```js
      {
        "nome": "Maria Oliveira",
        "cpf": "699.172.060-78",
        "telefone": "99887766551",
        "idade": 25,
        "genero": "FEMININO"
    }
```
### Exemplo de Resposta

```js
{
     {
        "id": 1,
        "nome": "Maria Oliveira",
        "cpf": "699.172.060-78",
        "telefone": "99887766551",
        "idade": 25,
        "genero": "FEMININO"
    }
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|201| Paciente cadastrado com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição

---

### Apagar Paciente

`DELETE` /paciente/`{id}`

Apaga o paciente com o `id` informado no parametro do path

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Paciente apagado com sucesso
|404| O paciente não foi encontrado, Verifique o `id` informado	
---

### Detalhar Paciente

`GET` /paciente/`{id}`

Retorna os dados do paciente com o `id` informado no parametro do path

### Exemplo de Resposta

```js

  {
        "id": 1,
        "nome": "Maria Oliveira",
        "cpf": "699.172.060-78",
        "telefone": "99887766551",
        "idade": 25,
        "genero": "FEMININO"
    }
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Paciente retornado com sucesso
|404| Não existe paciente com o `id` informado
---

### Atualizar Paciente

`PUT` /paciente/`{id}`

Atualiza os dados do paciente com o `id` informado no path

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅| Nome do paciente|
|cpf|string|✅|cpf do paciente|
|telefone|string|✅|telefone paciente|
|idade|int|✅|idade do paciente|
|genero|string|✅|MASCULINO ou FEMININO


```js
    {
        "id": 1,
        "nome": "Maria Oliveira",
        "cpf": "699.172.060-78",
        "telefone": "99887766551",
        "idade": 25,
        "genero": "FEMININO"
    }
```
### Exemplo de Resposta

```js
 {
        "id": 1,
        "nome": "Maria Oliveira",
        "cpf": "699.172.060-78",
        "telefone": "99887766551",
        "idade": 25,
        "genero": "FEMININO"
    }
```
### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Paciente cadastrado com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição
|404| Não existe paciente com o `id` informado
---

### Listar Anamnesia

`GET` /usuarios

Retorna um array com todo historico medico do paciente

### Exemplo de Resposta

```js
    [
         {
        "id": 1,
        "lesoes": "Erupção cutânea na região dos braços e pernas",
        "genetica": "Histórico familiar de diabetes",
        "cronicas": "Hipertensão",
        "alergias": "Alergia à penicilina"
    }
    ]
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Anamnesia retornadas com sucesso
---

### Cadastrar Anamnesia

`POST` /anamnesia

Cadastra uma anamnesia com os dados enviados no corpo da requisição.

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|lesoes|string|✅|Lesões que o paciente já teve ou tem
|genetica|string|✅|Doenças geneticas do paciente
|cronicas|string|✅|Doenças cronicas do paciente
|alergia|string|✅|Alergia do paciente

```js
 {
        
        "lesoes": "Erupção cutânea na região dos braços e pernas",
        "genetica": "Histórico familiar de diabetes",
        "cronicas": "Hipertensão",
        "alergias": "Alergia à penicilina"
    }
```
### Exemplo de Resposta

```js
{
      {
        "id": 1,
        "lesoes": "Erupção cutânea na região dos braços e pernas",
        "genetica": "Histórico familiar de diabetes",
        "cronicas": "Hipertensão",
        "alergias": "Alergia à penicilina"
    }
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|201| Anamnesia cadastrada com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição

---

### Apagar Anamnesia

`DELETE` /anamnesia/`{id}`

Apaga a anamnesia com o `id` informado no parametro do path

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Anamnesia apagado com sucesso
|404| A anamnesia não foi encontrada, Verifique o `id` informado	
---

### Detalhar Anamnesia

`GET` /anamnesia/`{id}`

Retorna os dados da Anamnesia com o `id` informado no parametro do path

### Exemplo de Resposta

```js

{
    "id": 1,
    "lesoes": "Sem lesões significativas",
    "genetica": "Nenhuma doença genética    conhecida na família",
    "cronicas": "Sinusite",
    "alergias": "Nenhuma"
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Anamnesia retornado com sucesso
|404| Não existe anamnesia com o `id` informado
---
### Atualizar Anamnesia

`PUT` /anamnesia/`{id}`

Atualiza os dados da anamnesia com o `id` informado no path

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|lesoes|string|✅|Lesões que o paciente já teve ou tem
|genetica|string|✅|Doenças geneticas do paciente
|cronicas|string|✅|Doenças cronicas do paciente
|alergia|string|✅|Alergia do paciente
```js
   {
    "id": 1,
    "lesoes": "Sem lesões significativas",
    "genetica": "Nenhuma doença genética    conhecida na família",
    "cronicas": "Sinusite",
    "alergias": "Nenhuma"
}
```
### Exemplo de Resposta

```js
{
    {
    "id": 1,
    "lesoes": "Sem lesões significativas",
    "genetica": "Nenhuma doença genética    conhecida na família",
    "cronicas": "Sinusite",
    "alergias": "Nenhuma"
}
}
```
### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Anamnesia atualizado com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição
|404| Não existe Anamnesia com o `id` informado
---

### Listar Convenio

`GET` /convenio

Retorna uma lista de convenios

### Exemplo de Resposta

```js
    [
    {
        "id": 1,
        "nome": "Convenio A",
        "cnpj": "37.050.194/0001-40",
        "telefone": "1111111-1111",
        "ativo": true
    }
    ]
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Convenios retornados com sucesso
|401| Não autorizado. Realize a autenticaçãoem /login

---

### Lançar Convenio

`POST` convenio

Cria um convenio

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅|Nome do convenio
|cnpj|string|✅|cnpj valido
|telefone|string|✅|telefone do convenio
|ativo|boolean|✅|se o convenio é valido

```js
   {
 
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```
### Exemplo de Resposta

```js

    {
    "id": 1,
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|201| Convenio lançado com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição


---

### Atualizar Convenio

`PUT` /convenio`{id}`

Atualiza o convenio do produto com o `id` informado no parametro do path

### Corpo de Requisição


|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅|Nome do convenio
|cnpj|string|✅|cnpj valido
|telefone|string|✅|telefone do convenio
|ativo|boolean|✅|se o convenio é valido

```js
    
    {
    "id": 1,
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```
### Exemplo de Resposta

```js

    {
    "id": 1,
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Convenio apago com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição
|404| O convenio não foi encontrada, Verifique o `id` informado	
---

### Deletar Convenio

`DELETE` convenio`{id}`

Apaga o convenio do produto com o `id` informado no parametro do path


### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Convenio apagado com sucesso
|404| O convenio não foi encontrada, Verifique o `id` informado	
---

### Detalhar Convenio

`GET` /convenio`{id}`

Retorna os dados do convenio com o `id` informado no parametro do path

### Exemplo de Resposta

```js


    {
    "id": 1,
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|204| Convenio retornado com sucesso
|404| Não existe estoque com o `id` informado
---

### Listar Ficha Atendimento

`GET` /fichadeatendimento

Retorna uma lista com as fichas de atendimento

### Exemplo de Resposta

```js
    [
      {
        "id": 1,
        "peso": 70.5,
        "pressao": "120/80",
        "altura": 1.75,
        "temperatura": 37.2,
        "dores": "Dores leves nas costas",
        "entradaPaciente": "2024-04-16",
        "saidaPaciente": "2024-04-17",
        "ativo": true
    }
    ]
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|200| Ficha de atendimento retornados com sucesso
|401| Não autorizado. Realize a autenticaçãoem /login

---

### Lançar Ficha de Atendimento

`POST` convenio

Cria um convenio

### Corpo de Requisição

|campo|tipo|obrigatório|descrição|
|-----|----|:-----------:|---------|
|nome|string|✅|Nome do convenio
|cnpj|string|✅|cnpj valido
|telefone|string|✅|telefone do convenio
|ativo|boolean|✅|se o convenio é valido

```js
   {
 
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```
### Exemplo de Resposta

```js

    {
    "id": 1,
    "nome": "Convenio A",
    "cnpj": "37.050.194/0001-40",
    "telefone": "1111111-1111",
    "ativo": true
}
```

### Códigos de Resposta

| Código | descrição |
|--------|-----------|
|201| Convenio lançado com sucesso
|400| Validação falhou. Verifique os dados enviados da requisição


---
