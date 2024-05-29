document.addEventListener("DOMContentLoaded", () => {
  const divInsertPassword =
    document.getElementsByClassName("divInsertPassword")[0];
  const btnInsertPassword = document.getElementById("btnInsertPassword");
  btnInsertPassword.addEventListener("click", () => {
    divInsertPassword.classList.toggle("show");
  });

  const forma = document.getElementsByTagName("form");
  forma.addEventListener("submit", (event) => {
    event.preventDefault();
    const formData = new FormData(forma);
    fetch(forma.action, {
      method: "post",
      headers: {
        'Content-Type': 'application/json',
      },
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        console.log(data + "ovo je odgovor servera");
      })
      .catch(error => {
        console.log("Greška " + error);
      })
  })

});
