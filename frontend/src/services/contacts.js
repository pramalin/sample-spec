const API_BASE = '/api';

export async function getContacts(page = 0, size = 20) {
  const response = await fetch(`${API_BASE}/contacts?page=${page}&size=${size}`);
  if (!response.ok) throw new Error('Failed to fetch contacts');
  return response.json();
}

export async function getContact(id) {
  const response = await fetch(`${API_BASE}/contacts/${id}`);
  if (!response.ok) throw new Error('Failed to fetch contact');
  return response.json();
}

export async function createContact(contact) {
  const response = await fetch(`${API_BASE}/contacts`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(contact),
  });
  if (!response.ok) throw new Error('Failed to create contact');
  return response.json();
}

export async function updateContact(id, contact) {
  const response = await fetch(`${API_BASE}/contacts/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(contact),
  });
  if (!response.ok) throw new Error('Failed to update contact');
  return response.json();
}

export async function deleteContact(id) {
  const response = await fetch(`${API_BASE}/contacts/${id}`, {
    method: 'DELETE',
  });
  if (!response.ok) throw new Error('Failed to delete contact');
}

export async function searchContacts(query) {
  const response = await fetch(`${API_BASE}/contacts/search?q=${encodeURIComponent(query)}`);
  if (!response.ok) throw new Error('Failed to search contacts');
  return response.json();
}
