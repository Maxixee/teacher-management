## Microsserviço de gerenciamento de professores.

### Descrição
O MS producer usa um feign cliente pra buscar informação em um mock de uma API de Instrutores, ao usar o endpoit de 
listar todos os professores, essa lista é enviada por um topico kafka para o MS consumer, que consome a mensagem e 
salva a lista de professores num banco de dados postgres


### Como rodar
1. Primeiro clone o repositório

```
git clone https://github.com/guigann/ms-teachers-management.git
```

2. Suba o container do docker para rodas as imagens do Kafka e do Postgres
```
docker-compose -f docker-compose.yml up
```

3. Com o Swagger disponibilizado na pasta "Swagger" crie um mock pelo Mockoon, SOAPUI ou outro aplicativo <br>
   de sua preferência e deixe rodando
