import {combineReducers} from '@reduxjs/toolkit';

import formReducer from './features/formSlice';

const rootReducer = combineReducers({
  form: formReducer
});

export default rootReducer;
