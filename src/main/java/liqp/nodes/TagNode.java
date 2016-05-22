package liqp.nodes;

import liqp.parser.Flavor;
import liqp.tags.Include;
import liqp.tags.Tag;

import java.io.File;
import java.util.Map;

class TagNode implements LNode {

    private Tag tag;
    private LNode[] tokens;
    private Flavor flavor;

    public TagNode(String tagName, Tag tag, LNode... tokens) {
        this(tagName, tag, Flavor.LIQUID, tokens);
    }

    public TagNode(String tagName, Tag tag, Flavor flavor, LNode... tokens) {
        if (tag == null) {
            throw new IllegalArgumentException("no tag available named: " + tagName);
        }
        this.tag = tag;
        this.tokens = tokens;
        this.flavor = flavor;
    }

    @Override
    public Object render(Map<String, Object> context) {

        // Check if the INCLUDES_DIRECTORY_KEY has already been set, and if not,
        // set it based on the value in the flavor.
        if (!context.containsKey(Include.INCLUDES_DIRECTORY_KEY)) {
            context.put(Include.INCLUDES_DIRECTORY_KEY, new File(flavor.snippetsFolderName));
        }

        return tag.render(context, tokens);
    }
}
