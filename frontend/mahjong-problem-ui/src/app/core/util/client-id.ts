const STORAGE_KEY = 'ploutos_client_id';

export function getClientId(): string {
  if (typeof window === 'undefined' || !window.localStorage) {
    return '';
  }

  let id = window.localStorage.getItem(STORAGE_KEY);
  if (!id) {
    id = window.crypto?.randomUUID?.() ?? Math.random().toString(36).slice(2);
    window.localStorage.setItem(STORAGE_KEY, id);
  }
  return id;
}
