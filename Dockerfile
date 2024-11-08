# Usar uma imagem base do OpenJDK com suporte ao Tomcat
FROM tomcat:9.0.96-jdk22-openjdk

# Definir o diretório de trabalho dentro do container
WORKDIR /usr/local/tomcat/webapps/ROOT

# Copiar o conteúdo da pasta 'webapp' para o diretório do Tomcat
COPY webapp/ .

# Copiar o driver JDBC para o diretório de bibliotecas do Tomcat
COPY lib/ojdbc11.jar /usr/local/tomcat/lib/

# Expor a porta 8080 para acesso à aplicação
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh
