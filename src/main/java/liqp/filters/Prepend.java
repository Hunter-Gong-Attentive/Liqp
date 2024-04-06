package liqp.filters;

import liqp.TemplateContext;

public class Prepend extends Filter {

    /*
     * (Object) append(input, string)
     *
     * add one string to another
     */
    @Override
    public Object apply(Object value, TemplateContext context, Object... params) {

        return super.asString(super.get(0, params), context) + super.asString(value, context);
    }
}
