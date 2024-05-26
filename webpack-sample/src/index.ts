import {formData} from './forms';

const form = document.querySelector('form')!;

form.addEventListener('submit', (evt) => {
    evt.preventDefault();
    const data = formData(form);
    console.log(data);
});
