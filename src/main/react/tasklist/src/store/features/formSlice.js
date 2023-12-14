import { createSlice } from '@reduxjs/toolkit';

export const formSlice = createSlice({
    name: 'form',
    initialState: {
        name: '',
        email: '',
        message: '',
        errors: {},
    },
    reducers: {
        updateField: (state, action) => {
            state[action.payload.field] = action.payload.value;
        },
        setErrors: (state, action) => {
            state.errors = action.payload;
        },
        resetForm: state => {
            state.name = '';
            state.email = '';
            state.message = '';
            state.errors = {};
        },
    },
});
export const { updateField, setErrors, resetForm } = formSlice.actions;
export default formSlice.reducer;