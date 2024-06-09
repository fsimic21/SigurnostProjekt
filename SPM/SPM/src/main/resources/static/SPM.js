document.addEventListener("DOMContentLoaded", () => {
  const divInsertPassword = document.getElementsByClassName("divInsertPassword")[0];
  const btnInsertPassword = document.getElementById("btnInsertPassword");
  const generatePasswordBtn = document.getElementById("generatePasswordBtn");
  const passwordField = document.getElementById("password");
  const algorithmSelect = document.getElementById("algorithm");

  btnInsertPassword.addEventListener("click", () => {
    divInsertPassword.classList.toggle("show");
  });

  generatePasswordBtn.addEventListener("click", () => {
    const generatedPassword = generateRandomPassword();
    const userConfirmed = confirm(`Generated Password: ${generatedPassword}\nDo you want to use this password?`);
    if (userConfirmed) {
      passwordField.value = generatedPassword;
    }
  });

  const form = document.getElementsByTagName("form")[0];
  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const password = passwordField.value;
    const algorithm = algorithmSelect.value;
    let key;
    let url;

    switch (algorithm) {
      case "aes":
        key = generateRandomKey(32);
        url = "http://localhost:8080/passwords/aes";
        break;
      case "blowfish":
        key = generateRandomKey(32);
        url = "http://localhost:8080/passwords/blowfish";
        break;
      case "rsa":
        const rsaKey = await generateRSAKeyPair();
        key = rsaKey.publicKey;
        url = "http://localhost:8080/passwords/rsa";
        break;
      case "SHA-256":
        key = generateRandomKey(16);
        url = "http://localhost:8080/passwords/sha";
        break;
    }

    const data = {
      key: key,
      password: password
    };

    fetch(url, {
      method: "POST",
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        console.log("aaaaa")
        return response.JSON
      })
      .then(data => {
        fetchPasswords();
      })
      .catch(error => {
        console.log("Greška " + error);
      });
    
  });

  function generateRandomPassword() {
    const length = 12;
    const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+~`|}{[]:;?><,./-=";
    let password = "";
    for (let i = 0, n = charset.length; i < length; ++i) {
      password += charset.charAt(Math.floor(Math.random() * n));
    }
    return password;
  }

  function generateRandomKey(length) {
    const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    let key = "";
    for (let i = 0, n = charset.length; i < length; ++i) {
      key += charset.charAt(Math.floor(Math.random() * n));
    }
    return key;
  }

  async function generateRSAKeyPair() {
    const keyPair = await window.crypto.subtle.generateKey(
      {
        name: "RSA-OAEP",
        modulusLength: 2048,
        publicExponent: new Uint8Array([0x01, 0x00, 0x01]),
        hash: "SHA-256"
      },
      true,
      ["encrypt", "decrypt"]
    );

    const publicKey = await window.crypto.subtle.exportKey("spki", keyPair.publicKey);
    return {
      publicKey: bufferToBase64(publicKey),
      privateKey: keyPair.privateKey
    };
  }

  function bufferToBase64(buffer) {
    let binary = '';
    const bytes = new Uint8Array(buffer);
    const len = bytes.byteLength;
    for (let i = 0; i < len; i++) {
      binary += String.fromCharCode(bytes[i]);
    }
    return window.btoa(binary);
  }

  async function fetchPasswords() {
    try {
      const response = await fetch('http://localhost:8080/passwords/');
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const passwords = await response.json();
      if(passwords){
        console.log("Dohvacam sifre", passwords);
        populateTable(passwords);
      }
      
    } catch (error) {
      console.log("Error fetching passwords: " + error);
    }
  }

  function populateTable(passwords) {
    console.log("Populating table with passwords", passwords);
    const tbody = document.querySelector("#passwordTable tbody");
    tbody.innerHTML = '';
  
    passwords.forEach(password => {
      const row = document.createElement('tr');
  
      const hashedPasswordCell = document.createElement('td');
      hashedPasswordCell.textContent = password.password;
      row.appendChild(hashedPasswordCell);
  
      const hashTypeCell = document.createElement('td');
      hashTypeCell.textContent = password.hashType;
      row.appendChild(hashTypeCell);
  
      tbody.appendChild(row);
    });
  }
  

  fetchPasswords();
});