// Функция для посимвольного появления текста
function animateText(elementId, text, delay = 50) {
    const element = document.getElementById(elementId);
    let index = 0;
    element.textContent = ""; // очищаем содержимое
    element.style.opacity = 1; // делаем элемент видимым

    function showNextChar() {
        if (index < text.length) {
            // добавляем следующий символ
            element.textContent += text.charAt(index);
            index++;
            setTimeout(showNextChar, delay);
        }
    }

    showNextChar();
}

// Запуск анимации после загрузки страницы
window.onload = function() {
    const message = "12 КРИПТО_ЗАПИСОК";
    animateText("cryptoText", message, 100); // 100 мс между символами
};