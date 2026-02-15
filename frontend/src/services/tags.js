const API_BASE = '/api';

export async function getTags() {
  const response = await fetch(`${API_BASE}/tags`);
  if (!response.ok) throw new Error('Failed to fetch tags');
  return response.json();
}

export async function createTag(tag) {
  const response = await fetch(`${API_BASE}/tags`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(tag),
  });
  if (!response.ok) throw new Error('Failed to create tag');
  return response.json();
}

export async function updateTag(id, tag) {
  const response = await fetch(`${API_BASE}/tags/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(tag),
  });
  if (!response.ok) throw new Error('Failed to update tag');
  return response.json();
}

export async function deleteTag(id) {
  const response = await fetch(`${API_BASE}/tags/${id}`, {
    method: 'DELETE',
  });
  if (!response.ok) throw new Error('Failed to delete tag');
}
