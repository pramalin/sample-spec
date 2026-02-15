import * as contactsApi from '../services/contacts.js';

let currentPage = 0;
let isSearchMode = false;

document.addEventListener('DOMContentLoaded', () => {
  loadContacts();
  setupEventListeners();
});

function setupEventListeners() {
  document.getElementById('addContactBtn').addEventListener('click', () => openModal());
  document.getElementById('closeModal').addEventListener('click', closeModal);
  document.getElementById('cancelBtn').addEventListener('click', closeModal);
  document.getElementById('searchBtn').addEventListener('click', handleSearch);
  document.getElementById('searchInput').addEventListener('keypress', (e) => {
    if (e.key === 'Enter') handleSearch();
  });
  document.getElementById('contactForm').addEventListener('submit', handleSubmit);
  
  document.getElementById('contactModal').addEventListener('click', (e) => {
    if (e.target.id === 'contactModal') closeModal();
  });
}

async function loadContacts(page = 0) {
  showLoading(true);
  try {
    const data = await contactsApi.getContacts(page, 20);
    renderContacts(data.content || data);
    renderPagination(data);
  } catch (error) {
    showError(error.message);
  } finally {
    showLoading(false);
  }
}

async function handleSearch() {
  const query = document.getElementById('searchInput').value.trim();
  if (!query) {
    isSearchMode = false;
    loadContacts();
    return;
  }
  
  showLoading(true);
  isSearchMode = true;
  try {
    const contacts = await contactsApi.searchContacts(query);
    renderContacts(contacts);
    document.getElementById('pagination').innerHTML = '';
  } catch (error) {
    showError(error.message);
  } finally {
    showLoading(false);
  }
}

function renderContacts(contacts) {
  const container = document.getElementById('contactsList');
  
  if (!contacts || contacts.length === 0) {
    container.innerHTML = '<p class="empty-state">No contacts found. Add your first contact!</p>';
    return;
  }
  
  container.innerHTML = contacts.map(contact => `
    <div class="card contact-item" data-id="${contact.id}">
      <div class="contact-info">
        <h3>${escapeHtml(contact.name)}</h3>
        ${contact.phone ? `<p>📞 ${escapeHtml(contact.phone)}</p>` : ''}
        ${contact.email ? `<p>✉️ ${escapeHtml(contact.email)}</p>` : ''}
      </div>
      <div class="card-actions">
        <button class="btn btn-secondary edit-btn" data-id="${contact.id}">Edit</button>
        <button class="btn btn-danger delete-btn" data-id="${contact.id}">Delete</button>
      </div>
    </div>
  `).join('');
  
  container.querySelectorAll('.edit-btn').forEach(btn => {
    btn.addEventListener('click', () => editContact(btn.dataset.id));
  });
  
  container.querySelectorAll('.delete-btn').forEach(btn => {
    btn.addEventListener('click', () => deleteContact(btn.dataset.id));
  });
}

function renderPagination(data) {
  const container = document.getElementById('pagination');
  if (!data.totalPages || data.totalPages <= 1) {
    container.innerHTML = '';
    return;
  }
  
  let html = '';
  for (let i = 0; i < data.totalPages; i++) {
    html += `<button class="page-btn ${i === currentPage ? 'active' : ''}" data-page="${i}">${i + 1}</button>`;
  }
  container.innerHTML = html;
  
  container.querySelectorAll('.page-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      currentPage = parseInt(btn.dataset.page);
      loadContacts(currentPage);
    });
  });
}

function openModal(contact = null) {
  const modal = document.getElementById('contactModal');
  const title = document.getElementById('modalTitle');
  const form = document.getElementById('contactForm');
  
  if (contact) {
    title.textContent = 'Edit Contact';
    document.getElementById('contactId').value = contact.id;
    document.getElementById('name').value = contact.name;
    document.getElementById('phone').value = contact.phone || '';
    document.getElementById('email').value = contact.email || '';
    document.getElementById('notes').value = contact.notes || '';
  } else {
    title.textContent = 'Add Contact';
    form.reset();
    document.getElementById('contactId').value = '';
  }
  
  modal.classList.add('active');
}

function closeModal() {
  document.getElementById('contactModal').classList.remove('active');
}

async function editContact(id) {
  try {
    const contact = await contactsApi.getContact(id);
    openModal(contact);
  } catch (error) {
    showError(error.message);
  }
}

async function deleteContact(id) {
  if (!confirm('Are you sure you want to delete this contact?')) return;
  
  try {
    await contactsApi.deleteContact(id);
    loadContacts(currentPage);
  } catch (error) {
    showError(error.message);
  }
}

async function handleSubmit(e) {
  e.preventDefault();
  
  const id = document.getElementById('contactId').value;
  const contact = {
    name: document.getElementById('name').value,
    phone: document.getElementById('phone').value || null,
    email: document.getElementById('email').value || null,
    notes: document.getElementById('notes').value || null,
  };
  
  try {
    if (id) {
      await contactsApi.updateContact(id, contact);
    } else {
      await contactsApi.createContact(contact);
    }
    closeModal();
    loadContacts(currentPage);
  } catch (error) {
    showError(error.message);
  }
}

function showLoading(show) {
  document.getElementById('loading').style.display = show ? 'block' : 'none';
}

function showError(message) {
  const container = document.getElementById('contactsList');
  container.innerHTML = `<div class="error">${escapeHtml(message)}</div>`;
}

function escapeHtml(text) {
  const div = document.createElement('div');
  div.textContent = text;
  return div.innerHTML;
}
