/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

//handle table url
document.addEventListener("DOMContentLoaded", function () {
    const rows = document.querySelectorAll("table tbody tr");
    rows.forEach((row) => {
        row.addEventListener("click", function () {
            const href = this.getAttribute("data-href");
            if (href) {
                window.location.href = href;
            }
        });
    });
});
function addQuestion() {
    const questionsContainer = document.getElementById('questionsContainer');
    const questionCount = questionsContainer.children.length + 1;
    const questionDiv = document.createElement('div');
    questionDiv.className = 'question';
    questionDiv.setAttribute('data-question-number', questionCount);
    
    const label = document.createElement('label');
    label.setAttribute('for', `question${questionCount}`);
    label.textContent = `Câu hỏi ${questionCount}:`;
    
    const input = document.createElement('input');
    input.type = 'text';
    input.id = `question${questionCount}`;
    input.name = 'questions[]';
    input.required = true;
    
    const typeLabel = document.createElement('label');
    typeLabel.setAttribute('for', `questionType${questionCount}`);
    typeLabel.textContent = 'Loại câu hỏi:';
    
    const select = document.createElement('select');
    select.id = `questionType${questionCount}`;
    select.name = 'questionTypes[]';
    select.onchange = function() { addOptions(this, questionCount); };
    
    const textOption = document.createElement('option');
    textOption.value = 'text';
    textOption.textContent = 'Text';
    select.appendChild(textOption);
    
    const radioOption = document.createElement('option');
    radioOption.value = 'radio';
    radioOption.textContent = 'Radio';
    select.appendChild(radioOption);
    
    const checkboxOption = document.createElement('option');
    checkboxOption.value = 'checkbox';
    checkboxOption.textContent = 'Checkbox';
    select.appendChild(checkboxOption);
    
    const optionsContainer = document.createElement('div');
    optionsContainer.id = `optionsContainer${questionCount}`;
    
    questionDiv.appendChild(label);
    questionDiv.appendChild(input);
    questionDiv.appendChild(typeLabel);
    questionDiv.appendChild(select);
    questionDiv.appendChild(optionsContainer);
    
    questionsContainer.appendChild(questionDiv);
}

function addOptions(selectElement, questionNumber) {
    const optionsContainer = document.getElementById(`optionsContainer${questionNumber}`);
    optionsContainer.innerHTML = ''; // Clear existing options
    
    if (selectElement.value === 'radio' || selectElement.value === 'checkbox') {
        const addOptionButton = document.createElement('button');
        addOptionButton.type = 'button';
        addOptionButton.classList.add('addoption-btn');
        addOptionButton.textContent = 'Thêm tùy chọn';
        addOptionButton.onclick = function() { addOption(questionNumber, selectElement.value); };
        optionsContainer.appendChild(addOptionButton);
        addOption(questionNumber, selectElement.value);
    }
}

function addOption(questionNumber, optionType) {
    const optionsContainer = document.getElementById(`optionsContainer${questionNumber}`);
    const optionCount = optionsContainer.querySelectorAll('.option').length + 1;
    
    const label = document.createElement('label');
    label.setAttribute('for', `option${questionNumber}_${optionCount}`);
    label.textContent = `Tùy chọn ${optionCount}:`;
    
    const optionDiv = document.createElement('div');
    optionDiv.className = 'option';
    
    
    const input = document.createElement('input');
    input.type = 'text';
    input.id = `option${questionNumber}_${optionCount}`;
    input.name = `options${questionNumber}[]`;
    input.required = true;
    
    const optionInput = document.createElement('input');
    optionInput.type = optionType;
    optionInput.disabled = true; // Chỉ để hiển thị, không cần giá trị thực
    optionInput.className = 'input-icon';
    
    optionsContainer.appendChild(label);
    optionDiv.appendChild(input);
    optionDiv.appendChild(optionInput);
    
    optionsContainer.appendChild(optionDiv);
}

