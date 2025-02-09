## Tutorial: Construção e Publicação de Imagem Docker no Docker Hub

Passos necessários para criar uma imagem Docker, executar um container com variáveis de ambiente e publicar a imagem no Docker Hub.

---

### Passo 1: Criar a imagem a partir do Dockerfile
Use o comando abaixo para criar uma imagem com o nome `finances-ms-transaction-img`:
```bash
docker build -t finances-ms-transaction-img .
```
- O ponto (`.`) indica que o `Dockerfile` está no diretório atual.

---

### Passo 2: Executar o container com nome e arquivo .env
Inicie o container, definindo um nome e carregando variáveis de ambiente a partir do arquivo `.env`:
```bash
docker run --name finances-ms-transaction --env-file .env -p 8080:8080 finances-ms-transaction-img
```
- `--name finances-ms-transaction`: Define o nome do container.
- `--env-file .env`: Carrega as variáveis do arquivo `.env`.
- `-p 8080:8080`: Mapeia a porta 8080 do container para o host.

---

### Passo 3: Fazer login no Docker Hub
Certifique-se de estar logado no Docker Hub antes de publicar a imagem:
```bash
docker login
```
- Insira seu **nome de usuário** e **senha** quando solicitado.

---

### Passo 4: Renomear a imagem para o repositório no Docker Hub
Renomeie a imagem para o formato `nome-de-usuario/nome-da-imagem:versao`:
```bash
docker tag finances-ms-transaction-img gisellebarbosa/finances-ms-transaction:1.0
```
Exemplo:
```bash
docker tag finances-ms-transaction-img gisellebarbosa/finances-ms-transaction:1.0
```

---

### Passo 5: Subir a imagem para o Docker Hub
Envie a imagem para o repositório do Docker Hub:
```bash
docker tag finances-ms-transaction-img gisellebarbosa/finances-ms-transaction:1.0
```
Exemplo:
```bash
docker push gisellebarbosa/finances-ms-transaction:v1
```

---

### Verificação
Após o upload, acesse sua conta no [Docker Hub](https://hub.docker.com) e confirme se a imagem foi carregada corretamente.

---

Agora você pode reutilizar este tutorial sempre que necessário! 🚀
