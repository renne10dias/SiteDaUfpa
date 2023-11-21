function getURLParameters() {
  // Obter a URL atual
  var url = window.location.href;
  // Obter os parâmetros da URL
  var paramsString = url.substring(url.indexOf("?") + 1);
  var params = paramsString.split("&");
  var paramsObj = {};

  // Criar um objeto com os parâmetros
  params.forEach(function (param) {
    var paramParts = param.split("=");
    var paramName = decodeURIComponent(paramParts[0]);
    var paramValue = decodeURIComponent(paramParts[1]);
    paramsObj[paramName] = paramValue;
  });
  return paramsObj;
}


function loadDetail() {
  // Obter os parâmetros da URL
  var params = getURLParameters();
  var apiDetail = params.api;
  var noticiaId = params.id;


  // URL da API externa para obter os detalhes da notícia
  var apiUrl = apiDetail + noticiaId;

  // Realizar a requisição para a segunda API
  fetch(apiUrl)
    .then(response => response.json())
    .then(data => {
      // Elementos da página de detalhes
        var tituloDestaque = document.getElementById("destaqueTitulo");
        var dataDestaque = document.getElementById("destaqueData");
        var descricaoDestaque = document.getElementById("destaqueDescricao");
        var imagemDestaque = document.getElementById("destaqueImagem");
        var textoDestaque = document.getElementById("destaqueTexto");
        var idDestaque = document.getElementById("destaqueId");

        tituloDestaque.textContent = data.titulo;
        dataDestaque.textContent = data.data;
        descricaoDestaque.textContent = data.descricao;
        imagemDestaque.src = data.imagem;
        textoDestaque.textContent = data.texto;
        idDestaque.textContent = ",      ID DA NOTICIA: " + data.id;

    })
    .catch(error => {
      console.error("Erro ao buscar detalhes da notícia:", error);
      tituloElement.textContent = "Erro ao buscar detalhes da notícia";
    });
}

// Carregar os detalhes da notícia ao carregar a página
loadDetail();
