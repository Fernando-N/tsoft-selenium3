Feature: DemoQA curso selenium avanzado Tsoft 2021

  Background:
    Given ingreso a url "https://demoqa.com/"
    And hago clic en cuadro Book Store Application

  @Test01
  Scenario: Registro de usuario
    Given hago clic en boton Login
    And hago clic en boton New User
    And completo el formulario de registro
      | key                                  | value           |
      | firstName                            | Fernando        |
      | lastName                             | Neira           |
      #Cambiar el username en caso de que se quiera realizar prueba y actualizar el user en bd
      | userName                             | fneira          |
      | password                             | p455W0rD$Tr0n6  |
    And hago clic en reCaptcha
    And hago clic en boton registrarme
    Then visualizo alerta de registro correcto con texto "User Register Successfully."

    @Test02
    Scenario: Llenar formulario estudiante
      Given expando menu lateral Forms
      And hago clic en opcion Practice Form
      And hago clic en el boton submit
      And completo el formulario de estudiante con los datos de base de datos
      And agrego materias al formulario
        | Abbreviated       | FullText          |
        | Ma                | Maths             |
        | Comp              | Computer Science  |
        | Eng               | English           |
      And agrego hobbies al formulario
        | Hobbies |
        | Reading |
        | Music   |
      And hago clic en el boton submit
      Then visualizo modal de formulario enviado correctamente

      @Test03
      Scenario: Book store
        Given hago clic en boton Login
        And inicio sesión con cuenta de base de datos
        And agrego libros a mi coleccion
        | Title                                     |
        | Git Pocket Guide                          |
        | Designing Evolvable Web APIs with ASP.NET |
        | Speaking JavaScript                       |
        | Eloquent JavaScript, Second Edition       |
        | Understanding ECMAScript 6                |
        And ingreso a mi perfil
        Then valido que los libros de mi coleccion existan en base de datos
