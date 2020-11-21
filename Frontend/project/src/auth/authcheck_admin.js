export const authcheck_admin = (to, from, next) => {
  if(window.sessionStorage.getItem('isAdmin')){
    next()}
    else{next({path:'/'})}
  };