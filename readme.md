# ViaVarejo test app

Aplicativo criado com as seguintes telas:
-   Home;
-   Listagem de produtos;
-   Detalhe do produto.


# Arquitetura

A arquitetura escolhida foi ***Model View ViewModel (MVVM)*** juntamente com o pattern ***Repository***

![](https://lh3.googleusercontent.com/RVQ8vju1b6kbsM0ZZE0LrZmKhLc9yNUnhvA0rs5USkIEZjscH4dJczpXeHa90KTuAynBZ2zqtNxTNfSKyRvWOWUaI6E0m1_pNsnWe6bnDFQOHhN4cESLjAVGI31ywBZNt85biRwKQOVLl40y2RAgzHOWhmk0h8veu7zqUwaNETqAjiEibo81zSzt-oPHp3e6zIPGuEnT2wq6_YeeyJwsI0t8tI7cQqfKempo64lxrhWMSYYD7CrDhzTPPXLEM_mGxFHqmeKHDgO3mBR0GP4EU8FlH8Ky52l48Ppif14z1ei0pPkmon9ZA9RQqNuFa5_TfEALgon7iQAdZhHtJQZP7zVi4SHFz_iBx7Wdw2ksTCz3DjGjv5zWozi6z_4ogk8wIMwWqGVE-NetA0S20bDd2IWqwnmGigDnVfnnSZCzzXzdmVg7_wcYZp_69kqYuiy_mOgiALYGT47Sbi0yjPDrNHeVWjVAs-qU2r3OG5XnbjW2HmNShPy8w6KlbDi9105enjektgUdgccQY-UzVT_0qyTqR1eTUOySPKxjKv1hp9IDSUbZoWfAJ_7cLLi8zpt03juZxHbLVLDyJnXwih_g8dUfFO9QGqyJz3A1DwxBfsfKvSW64GxKpNRBNKx_OtUr90Afwj00BgqLqAU4hQrzVmTWd9umnaxTH5MsP5P8zmSmROqz60jgdtrcwDCpWk6IDZrAOIpdYP6rG1OuLT4aHVCRzoYAfhiQFKd0W32g8V_WwzsrMA=w413-h373-no)

- ***Model*** modelo dos dados.
- ***View*** é a entidade responsável pela aparência do que será exibido na tela. Dentro do nosso contexto, as Views são nossas Activities, Fragments e elementos visuais criados para serem disponibilizados na tela.
- ***ViewModel*** onde fica toda a regra de negócio da view, é responsável por manipular os dados que serão utilizados pela view. Utiliza databind para notificar os observadores.

## Bibliotecas

Foi utilizado as seguintes bibliotecas:
- Dagger: utilizado para injeção de dependência
-   Constraint Layout: projeto full constraint layout
-   Retrofit: consumo de serviços
-   Rx: programação reativa, permite criar programas assíncronos
-   Glide: usado para o carregamento de imagens
-   Junit: utilizado nos testes unitários
-   Mockito: utilziado para mocks nos testes unitários.

## Build Variants
Foi criado dois ambientes para o projeto:
- hml
- prod

## Integração com Bitrise

Segue o link da aplicação no bitrise: [https://app.bitrise.io/app/a099137a6b82a41d#/builds](https://app.bitrise.io/app/a099137a6b82a41d#/builds)

