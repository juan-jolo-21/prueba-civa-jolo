#Pasos para desplegar proyecto:

1. Tener java 17, maven y posgresql en windows
2. En postgres, crear la base de datos 'civa'
3. Abrir consola dentro de la carpeta buses y ejecutar mvn clean install
4. Ejecutar el proyecto spring boot
5. Luego; en posgres, copiar y ejecutar el siguiente scrpit:
      insert into modelo (fabricante,modelo)
      values ('volvo','B450');
      
      insert into modelo (fabricante,modelo)
      values ('mercedez-benz','O500RSD');
      
      insert into modelo (fabricante,modelo)
      values ('scania','K410');
      
      insert  into categoria_comercial (branding,asientos,detalles)
      values ('econociva',30,'servicio económico');
      
      insert  into categoria_comercial (branding,asientos,detalles)
      values ('excluciva',45,'180º bus cama');
      
      insert  into categoria_comercial (branding,asientos,detalles)
      values ('superciva',45,'servicio estandar');
   6. A través del enpoint "http://localhost:7777/bus", colocar los datos de prueba cada uno. los datos están en 'all-test-data.json'
   7. Ya se puede probar las peticiones get y get/{id}
   8. Abrir la carpeta front-civa en consola, luego hacer npm install y despues npm run dev
