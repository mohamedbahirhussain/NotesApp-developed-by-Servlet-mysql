// Function to get URL parameters
function getURLParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

// Get the username from the URL and display it
const username = getURLParameter('username');
const usernameElement = document.getElementById('username');

if (username) {
    usernameElement.textContent = username;
} else {
    usernameElement.textContent = username;
}
