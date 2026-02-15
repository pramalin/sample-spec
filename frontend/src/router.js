const routes = {
  '/': '/pages/contacts.html',
  '/contacts': '/pages/contacts.html',
  '/categories': '/pages/categories.html',
  '/tags': '/pages/tags.html',
};

export function initRouter() {
  const path = window.location.pathname;
  const route = routes[path] || routes['/'];
  
  if (route && window.location.pathname !== route) {
    window.location.href = route;
  }
}
