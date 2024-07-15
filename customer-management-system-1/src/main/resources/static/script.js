const loginForm = document.getElementById('login-form');
const registerForm = document.getElementById('register-form');
const customersTable = document.getElementById('customers-table');
const syncBtn = document.getElementById('sync-btn');

loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    try {
        const response = await fetch('/api/authenticate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        });
        const token = await response.json();
        localStorage.setItem('token', token);
        document.getElementById('login-response').innerHTML = 'Logged in successfully!';
    } catch (error) {
        document.getElementById('login-response').innerHTML = 'Invalid username or password';
    }
});

registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    try {
        const response = await fetch('/api/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password, firstName, lastName }),
        });
        const user = await response.json();
        document.getElementById('register-response').innerHTML = 'Registered successfully!';
    } catch (error) {
        document.getElementById('register-response').innerHTML = 'Registration failed';
    }
});

syncBtn.addEventListener('click', async () => {
    try {
        const token = localStorage.getItem('token');
        const response = await fetch('/api/customers', {
            method: 'GET',
            headers: { Authorization: `Bearer ${token}` },
        });
        const customers = await response.json();
        customersTable.innerHTML = '';
        customers.forEach((customer) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>${customer.email}</td>
                <td>${customer.phone}</td>
                <td>
                    <button class="edit-btn" data-id="${customer.id}">Edit</button>
                    <button class="delete-btn" data-id="${customer.id}">Delete</button>
                </td>
            `;
            customersTable.appendChild(row);
        });
    } catch (error) {
        console.error(error);
    }
});

customersTable.addEventListener('click', async (e) => {
    if (e.target.classList.contains('edit-btn')) {
        const id = e.target.dataset.id;
        try {
            const response = await fetch(`/api/customers/${id}`, {
                method: 'GET',
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
            });
            const customer = await response.json();
            // display edit form
        } catch (error) {
            console.error(error);
        }
    } else if (e.target.classList.contains('delete-btn')) {
        const id = e.target.dataset.id;
        try {
            const response = await fetch(`/api/customers/${id}`, {
                method: 'DELETE',
                headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
            });
            if (response.ok) {
                // remove row from table
            } else {
                console.error('Error deleting customer');
            }
        } catch (error) {
            console.error(error);
        }
    }
});