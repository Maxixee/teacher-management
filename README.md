## Microsserviço de gerenciamento de professores.

### Descrição
O MS producer usa um feign cliente pra buscar informação em um mock de uma API de Instrutores, ao usar o endpoit de <br>
listar todos os professores, essa lista é enviada por um topico kafka para o MS consumer, que consome a mensagem e <br>
salva a lista de professores num banco de dados postgres


### Como rodar
1. Primeiro clone o repositório

```
git clone https://github.com/guigann/ms-teachers-management.git
```

3. Suba o container do docker para rodas as imagens do Kafka e do Postgres
```
docker-compose -f docker-compose.yml up
```