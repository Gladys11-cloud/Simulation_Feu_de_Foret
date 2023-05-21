# Plantilla LaTeX - Formato INF PUCV

**Descripción:** Esta plantilla LaTeX no oficial ha sido desarrollada para cumplir con los estándares exigidos por el documento oficial de la Escuela de Ingeniería Informática (INF) de la Pontificia Universidad Católica de Valparaíso (PUCV).

![LaTeX][latex-badge] [![overleaf-badge]][overleaf-web]

## Tabla de contenidos <!-- omit from toc -->

- [Crear documento a partir de la plantilla](#crear-documento-a-partir-de-la-plantilla)
  - [A partir del template de Github](#a-partir-del-template-de-github)
  - [A partir del template de Overleaf](#a-partir-del-template-de-overleaf)
- [Base](#base)
- [Características](#características)
  - [Agregar referencia a bibliografía](#agregar-referencia-a-bibliografía)
  - [Incorporar elementos](#incorporar-elementos)
    - [Tablas](#tablas)
    - [Imágenes](#imágenes)
    - [Anexo (archivo PDF externo)](#anexo-archivo-pdf-externo)
- [Colaborar](#colaborar)

## Crear documento a partir de la plantilla

### A partir del template de Github

Para crear un nuevo repositorio a partir de esta plantilla, sigue estos pasos:

1. Dirígete al repositorio oficial en https://github.com/sebaignacioo/latex-inf.
2. Haz clic en el botón "Use this template" (Usar esta plantilla) ubicado en la parte superior derecha del repositorio.
3. Elige un nombre para tu nuevo repositorio y decide si quieres que sea público o privado.
4. Haz clic en "Create repository from template" (Crear repositorio a partir de la plantilla).

### A partir del template de Overleaf

Para utilizar esta plantilla en Overleaf, sigue estos pasos:

1. Dirígete a [este enlace](https://www.overleaf.com/read/gjqppvvkrjhj##) para acceder al proyecto de la plantilla en Overleaf.
2. Inicia sesión en Overleaf o crea una cuenta si aún no tienes una.
3. Haz clic en el botón "Clone this project" (Clonar este proyecto) en la parte superior derecha del proyecto.
4. Elige un nombre para tu nuevo proyecto y haz clic en "Clone Project" (Clonar proyecto).

De esta forma, tendrás una copia de la plantilla en tu cuenta de Overleaf, lista para ser editada y compilada.

## Base

La base para crear esta plantilla fue el archivo PDF oficial de la Escuela de Ingeniería Informática (INF) de la Pontificia Universidad Católica de Valparaíso (PUCV), que se encuentra en [este enlace](https://www.inf.ucv.cl/wp-content/uploads/2020/05/Formato_informes_Ing_Inf_2010.pdf).

**CONSIDERACIÓN**: El formato de bibliografía utilizado es APA, 7ma edición. No se cumple lo especificado en el documento oficial.

## Características

### Agregar referencia a bibliografía

Para agregar una nueva referencia a la bibliografía, sigue estos pasos:

1. Abre el archivo `referencias.bib` en tu editor de texto.
2. Añade la nueva referencia utilizando la sintaxis adecuada de BibTeX. Por ejemplo:
   ```
   @book{ejemplo,
   title={Título del libro},
   author={Autor, Nombre},
   year={Año de publicación},
   publisher={Editorial}
   }
   ```
3. Guarda los cambios en el archivo `referencias.bib`.
4. Para citar la referencia en tu documento LaTeX, utiliza el comando `\parencite{clave}` donde `clave` corresponde a la clave de la referencia en el archivo `referencias.bib` (en este caso, `ejemplo`).

### Incorporar elementos

#### Tablas

Para agregar tablas, utiliza el comando personalizado `\tabla` y el comando `\filaTabla` de la siguiente manera:

```
\tabla{Tabla de ejemplo}{tabla1}{|c|c|c|}{%
    \filaTabla{\textbf{Columna 1} & \textbf{Columna 2} & \textbf{Columna 3}}
    \filaTabla{10 & 5.6 & A}
    \filaTabla{25 & 3.2 & B}
    \filaTabla{8 & 7.1 & C}
    \filaTabla{12 & 9.5 & D}
    \filaTabla{20 & 2.3 & E}
}
```

#### Imágenes

Para agregar imágenes, utiliza el comando personalizado `\imagen` de la siguiente manera:

```
\imagen{ruta/imagen.jpg}{Título de la imagen}{label}
```

Ten en cuenta que las imágenes deben estar ubicadas en la carpeta `assets/images`.

#### Anexo (archivo PDF externo)

Para agregar archivos PDF externos, utiliza el comando personalizado `\pdfExterno` de la siguiente manera:

```
\pdfExterno{ruta/archivo.pdf}{Descripción}{label}
```

Ten en cuenta que los archivos PDF deben estar ubicados en la carpeta `assets/pdf`.

## Colaborar

Si eres estudiante o alumni en la Escuela de Ingeniería Informática de la PUCV, te animamos a colaborar para mejorar y pulir esta plantilla. Para ello, sigue estos pasos:

1. Haz un fork del repositorio original en https://github.com/sebaignacioo/latex-inf.
2. Clona tu fork en tu computadora y realiza las mejoras o cambios que consideres necesarios.
3. Haz commit de tus cambios y envía tus cambios a tu fork en GitHub.
4. Crea un pull request en el repositorio original, describiendo las mejoras o cambios que has realizado.

Si no tienes experiencia previa con GitHub, te recomendamos revisar guías y tutoriales sobre cómo usar Git y GitHub antes de colaborar. Puedes comenzar con la documentación oficial de GitHub sobre [cómo crear un Pull Request](https://docs.github.com/es/github/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request).

<!-- Esto va siempre al final -->

[latex-badge]: https://img.shields.io/badge/latex-%23008080.svg?style=for-the-badge&logo=latex&logoColor=white
[overleaf-badge]: https://img.shields.io/badge/Overleaf-47A141?logo=overleaf&logoColor=fff&style=for-the-badge
[overleaf-web]: https://www.overleaf.com/read/gjqppvvkrjhj##
