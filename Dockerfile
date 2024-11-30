#Criando uma máquina virtual com JDK21
FROM openjdk:21

#Criando uma pasta no conteiner para onde o projeto será publicado
WORKDIR /app

#Copiando todos os arquivos do projeto para dentro da pasta do conteiner
COPY . /app

#Comando para realizar o DEPLOY do projeto (publicação)
RUN ./mvnw -B clean package

#Informar a porta em que o projeto irá rodar no conteiner
EXPOSE 8084

#Comando para rodar o projeto
CMD ["java", "-jar", "target/apiProdutos-0.0.1-SNAPSHOT.jar"]