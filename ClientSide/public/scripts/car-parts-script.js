// Script to handle car part form and car part data
const carPartForm = document.getElementById('car-part-form');

carPartForm.addEventListener('submit', validateSubmitForm);

function validateSubmitForm(event) {
        // Prevent form submission
        event.preventDefault();
    
        const partName = document.getElementById('part-name').value;
        const partNumber = document.getElementById('part-number').value;
        const carId = document.getElementById('car-id').value;
        const manufacturerId = document.getElementById('manufacturer-id').value;
        const price = document.getElementById('price').value;
        const stockQuantity = document.getElementById('stock-quantity').value;

        const errorElement = document.querySelector('.error')
        errorElement.textContent = '';
        errorElement.classList.remove('show-error');

        // Validate form values

        if (isNaN(carId) || parseInt(carId) <= 0) {
            errorElement.textContent = 'Please make sure that the Car ID is a positive number';
            errorElement.classList.add('show-error');
            return;
        }
        
        if (isNaN(manufacturerId) || parseInt(manufacturerId) <= 0) {
            errorElement.textContent = 'Please make sure that the Manufacturer ID is a positive number';
            errorElement.classList.add('show-error');
            return;
        }

        if (isNaN(price) || parseFloat(price) <= 0) {
            errorElement.textContent = 'Please make sure that the Price is a positive number';
            errorElement.classList.add('show-error');
            return;
        }

        if (isNaN(stockQuantity) || parseInt(stockQuantity) <= 0) {
            errorElement.textContent = 'Please make sure that the Stock quantity is a positive number';
            errorElement.classList.add('show-error');
            return;
        }
    
        const newPart = {
            partName,
            partNumber,
            carId,
            manufacturerId,
            price: parseFloat(price).toFixed(2),
            stockQuantity 
        };
    
        // Add the new part to the parts list
        addPartToList(newPart);
    
        // Submit form
        // carPartForm.action = "addCarPart";
        // carPartForm.submit();


        carPartForm.reset()
}

function addPartToList(part) {
    const partsContainer = document.getElementById('parts-container');

    const partElement = document.createElement('div');
    partElement.classList.add('part');

    const partHeading = document.createElement('h3');
    partHeading.textContent = part.partName;
    partElement.appendChild(partHeading);

    const partNumber = document.createElement('p');
    partNumber.textContent = "Part number: " + part.partNumber;
    partElement.appendChild(partNumber);

    const carId = document.createElement('p');
    carId.textContent = "Car ID: " + part.carId;
    partElement.appendChild(carId);

    const manufacturerId = document.createElement('p');
    manufacturerId.textContent = "Manufacturer ID: " + part.manufacturerId;
    partElement.appendChild(manufacturerId);

    const priceValue = document.createElement('p');
    priceValue.textContent = "Price: Rs. " + part.price;
    partElement.appendChild(priceValue);

    const stockQuantity = document.createElement('p');
    stockQuantity.textContent = "Stock quantity: " + part.stockQuantity;
    partElement.appendChild(stockQuantity);

    partsContainer.appendChild(partElement);
}
